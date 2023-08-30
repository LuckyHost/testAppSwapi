package com.example.testapp.present

import android.util.*
import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import com.example.testapp.data.*
import com.example.testapp.data.Room.*
import com.example.testapp.domian.Room.DataClass.Films.*
import com.example.testapp.domian.Room.DataClass.Peoples.*
import com.example.testapp.domian.Room.DataClass.Planets.*
import com.example.testapp.domian.Room.DataClass.Starships.*
import dagger.hilt.android.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.*

@HiltViewModel
class ViewModel @Inject constructor(
    private val repository: Repository,
    private val daoPeople: DaoPeople,
    private val daoPlanet: DaoPlanet,
    private val daoStartShip: DaoStartShip,
    private val daoFilm: DaoFilm,
) : ViewModel() {


    private val _isLoadFile: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoadFile: StateFlow<Boolean> = _isLoadFile.asStateFlow()

    private var _listPeople = MutableStateFlow<List<ResultPeople>?>(null)
    val listpeople = _listPeople.asStateFlow()

    private var _listPlanet = MutableStateFlow<List<ResultPlanet>?>(null)
    val listPlanet = _listPlanet.asStateFlow()

    private var _listStartShip = MutableStateFlow<List<ResultStarShip>?>(null)
    val listStartShip = _listStartShip.asStateFlow()

    private var _listFilm = MutableStateFlow<List<ResultFilm>?>(null)
    val listFilm = _listFilm.asStateFlow()


    private var _searchTextDb = MutableStateFlow<String>("")
    var searchTextDb = _searchTextDb.asStateFlow()

    val checkToast: MutableStateFlow<Boolean> = MutableStateFlow(false)


    fun reLists(string: String) {
        _searchTextDb.value = string
        viewModelScope.launch(Dispatchers.IO) {

            async {
                daoPeople.getAll()
                    .map {
                        it.filter {
                            it.name.lowercase().startsWith(searchTextDb.value.lowercase())
                        }
                    }
                    .collect {
                        _listPeople.value = it
                        Log.d("MyLog", "ViewModel.kt.  it: ${searchTextDb.value}")
                        Log.d("MyLog", "ViewModel.kt. : Room Collect People")
                    }
            }

            async {
                daoPlanet.getAll().map {
                    it?.filter {
                        it.name.lowercase().startsWith(searchTextDb.value.lowercase())
                    }
                }.collect {
                    _listPlanet.value = it
                    Log.d("MyLog", "ViewModel.kt.  Size: ${it?.size}")
                    Log.d("MyLog", "ViewModel.kt.: Room Collect Planet")
                }

            }

            async {
                daoStartShip.getAll()?.map {
                    it.filter {
                        it.name.lowercase().startsWith(searchTextDb.value.lowercase())
                    }
                }?.collect {
                    Log.d("MyLog", "ViewModel.kt.  Size: ${it.size}")
                    _listStartShip.value = it;Log.d(
                    "MyLog",
                    "ViewModel.kt. : Room Collect StartShip"
                )
                }
            }

            async {
                daoFilm.getAll().collect {
                    Log.d("MyLog", "ViewModel.kt.  Size: ${it.size}")
                    _listFilm.value = it;Log.d("MyLog", "ViewModel.kt. : Room Collect StartShip")
                }
            }

        }
    }

    private suspend fun <T> test3 (
        getId: suspend ()->T?,
        updateFavorite:(T?)-> Unit
    )
    {
     updateFavorite(getId())

    }
    fun updateFavoritePeople(resultPeople: ResultPeople) {

        viewModelScope.async {
//
//            test3(
//                getId = {daoPeople.getById(resultPeople.name)},
//                updateFavorite = {it?.isFavorites=!it?.isFavorites!! }
//            )

            val idPerson = daoPeople.getById(resultPeople.name)
            idPerson?.isFavorites = !idPerson!!.isFavorites
            daoPeople.updateForFavorite(idPerson)
            Log.d("MyLog", "Функция Update Favorite Выполнена")
        }
    }

    fun updateFavoriteStarShipe(resultStarShipRoom: ResultStarShip) {

        viewModelScope.launch {
            val idPerson = daoStartShip.getById(resultStarShipRoom.name)
            idPerson!!.isFavorites = !idPerson.isFavorites
            daoStartShip.updateFaforiteStartships(idPerson)
            Log.d("MyLog", "Функция Update Favorite Выполнена")
        }
    }






    fun updateFavoritePlanet(resultPlanet: ResultPlanet) {

        viewModelScope.launch {

//            val idPerson = daoPlanet.getById(resultPlanet.name)
//            idPerson!!.isFavorites = !idPerson.isFavorites
//            daoPlanet.updateFaforitePlanet(idPerson)
//            Log.d("MyLog", "Функция Update Favorite Выполнена")
        }
    }


    val gelLoadAllData =
        viewModelScope.launch(Dispatchers.IO) {
            launch { repository.getDataAllPeopleNet(); Log.d("MyLog", "ViewModel.kt. gelLoadAllData: PeopleNet") }
            launch { repository.getDataAllPlanetNet(); Log.d("MyLog", "ViewModel.kt. gelLoadAllData: PlanetNet") }
            launch { repository.getDataAllStartShipNet(); Log.d("MyLog", "ViewModel.kt. gelLoadAllData: StartShipNet") }
            launch { repository.getDataAllFilm(); Log.d("MyLog", "ViewModel.kt. gelLoadAllData: FilmNet") }
                .join()
            _isLoadFile.value = true
            reLists(searchTextDb.value)
        }


    val listSearch: Flow<List<String?>> = flow {
        val result:MutableList<String> =  mutableListOf()
        listpeople.value?.forEach { result.add(it.name) }
        listPlanet.value?.forEach { result.add(it.name) }
        listStartShip.value?.forEach { result.add(it.name) }
        emit(result)
    }


    fun getNameFilms(resultPeople: ResultPeople): MutableStateFlow<String> {
        var resul = ""

        viewModelScope.async {

            async {
                resultPeople.films.map {
                    var filmData = daoFilm.getById(it)
                    resul = resul + (filmData!!.title) + (": " + filmData.producer + " ") + ("\n")
                }
            }.await()
            Log.d("MyLog", "ViewModel.kt. getFilmsPeople: RESULT ${resultPeople.name} $resul")


        }
        return MutableStateFlow(resul)


    }


}