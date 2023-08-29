
package com.example.testapp.present.Screens

import android.util.*
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.*
import androidx.navigation.*
import com.example.testapp.R
import com.example.testapp.present.*
import com.example.testapp.present.theme.*

const val actLazyPeople = 0
const val actLazyPlanet = 1
const val actLazyStartShip = 2


//@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun Home(viewModel: ViewModel, navController: NavController) {



    //list
    val listItemsPeopleLazy = viewModel.listpeople.collectAsState()
    val listItemStarShipeLazy = viewModel.listStartShip.collectAsState()
    val listItemPlaneteLazy = viewModel.listPlanet.collectAsState()
    val searchListString = viewModel.listSearch.collectAsState(emptyList())
    var reSearchListString = searchListString
//    var
    var searchText by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(false) }
    var isActLazyStarShip by remember { mutableStateOf(actLazyPeople) }
//    Animathion
    val rotationAngleAnim by animateFloatAsState(
        targetValue = if (isActive) 360f else 0f,
        animationSpec = if (isActive) tween(durationMillis = 1000) else {
            tween(durationMillis = 1000)
        },
        label = ""
    )
    val sizeAnim by animateDpAsState(
        targetValue = if (isActive) 30.dp else 0.dp,
        animationSpec = tween(durationMillis = 500), label = ""
    )
//


    fun actLazy(string: String) {

        var inListPeople = listItemsPeopleLazy.value?.filter {
            it.name.lowercase().startsWith(string.lowercase())
        }?.size

        Log.d("MyLog", "Home.kt. actLazy inListPeople : $inListPeople")

        var inListStartShipe = listItemStarShipeLazy.value?.filter {
            it.name.lowercase().startsWith(string.lowercase())
        }?.size

        Log.d("MyLog","Home.kt. actLazy inListStartShipe : $inListStartShipe")

        var inListPlanete = listItemPlaneteLazy.value?.filter {
            it.name.lowercase().startsWith(string.lowercase())
        }?.size

        Log.d("MyLog","Home.kt. actLazy inListPlanete : $inListPlanete")

        if (inListPeople != null && inListPlanete != null && inListStartShipe != null) {
            android.util.Log.d("MyLog","Home.kt. actLazy:  TEST")


            if ((inListPeople > inListStartShipe) || (inListPeople > inListPlanete)){
                isActLazyStarShip = actLazyPeople
            }

            if ((inListPlanete > inListStartShipe) || (inListPlanete > inListPeople)) {
                isActLazyStarShip = actLazyPlanet
            }

            if ((inListStartShipe > inListPlanete) || (inListStartShipe > inListPeople)){
                isActLazyStarShip = actLazyStartShip
            }

            Log.d("MyLog", "Home.kt. actLazyStartShip: isActLazyStarShip: $isActLazyStarShip")
        }
    }



    fun reListSearch(string: String) {
        var newListSearch = searchListString.value.filter {
            it?.lowercase()!!.startsWith(string.lowercase())
        }
        reSearchListString = mutableStateOf(newListSearch)

    }


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("Favorite") { popUpTo(route = "Home") }
                viewModel.reLists("")
            }) {
                Icon(Icons.Default.Favorite, contentDescription = null, tint = Pure)
            }
        }
    ) {


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        )
        {

            Column(modifier = Modifier.fillMaxSize()) {


                DockedSearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                    trailingIcon = {
                        if (isActive) {
                            IconButton(onClick = {
                                searchText = "";viewModel.reLists(searchText)
                            })
                            {
                                Icon(
                                    Icons.Filled.Clear,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .rotate(rotationAngleAnim)
                                        .size(sizeAnim)
                                )
                            }
                        }
                        else{
                            when (isActLazyStarShip) {
                                actLazyPeople -> {
                                    Icon(Icons.Default.Face, contentDescription = null)
                                }

                                actLazyPlanet -> {
                                    Icon(
                                        painterResource(id = R.drawable.planet),
                                        contentDescription = null
                                    )
                                }

                                actLazyStartShip -> {
                                    Icon(
                                        painterResource(id = R.drawable.air),
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    },
                    query = searchText,
                    placeholder = { Text(text = "Search....") },
                    onQueryChange = {
                        searchText = it
                        reListSearch(searchText)
                        actLazy(searchText)
                        viewModel.reLists(searchText)
                        Log.d("MyLog", "Home.kt. Home: Вводимое слово $searchText")
                    },

                    onSearch = { isActive = false },
                    active = isActive,
                    onActiveChange = { isActive = it},

                ) {

                    /*if (searchText.length > 0) {*/


                        LazyColumn(modifier = Modifier.fillMaxWidth()) {
                            items(reSearchListString.value) { text ->
                                text?.let {

                                        Text(text = it,
                                            modifier = Modifier
                                                .clickable {
                                                    searchText = it
                                                    isActive = false
                                                    actLazy(searchText)
                                                    viewModel.reLists(searchText)
                                                    Log.d("MyLog", "Home.kt. Home:  $it")
                                                }
                                                .padding(5.dp)
                                                .fillMaxWidth()
                                        )
                                }
                            }
                        }
                    /*}*/
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(listItemsPeopleLazy.value ?: emptyList()) {
                        ItemPerson(it, viewModel)
                    }
                    items(listItemPlaneteLazy.value ?: emptyList()) {
                        ItemPlanet(it, viewModel)
                    }
                    items(listItemStarShipeLazy.value ?: emptyList()) {
                        ItemStarship(it, viewModel)
                    }
                }
            }
        }


    }
}








