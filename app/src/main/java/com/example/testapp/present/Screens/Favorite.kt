package com.example.testapp.present.Screens

import android.annotation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import androidx.navigation.*
import com.example.testapp.R
import com.example.testapp.present.*
import com.example.testapp.present.theme.*

const val actPeople = 0
const val actPlanet = 1
const val actStartShip = 2


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun Favorite(viewModel: ViewModel) {
    var actLazy by remember { mutableStateOf(actPeople) }

    val listperson = viewModel.listpeople.collectAsState(listOf())
    val liststartship = viewModel.listStartShip.collectAsState(listOf())
    val listPlanet = viewModel.listPlanet.collectAsState(listOf())


    Scaffold(
        bottomBar = {
            BottomAppBar {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp, end = 40.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { actLazy = actLazyPeople }) {

                        if (actLazy == actLazyPeople) {

                            Icon(
                                Icons.Default.Face, contentDescription = null,
                                modifier = Modifier.size(60.dp),
                                tint = Purple40
                            )
                        } else {
                            Icon(
                                Icons.Default.Face, contentDescription = null,
                                modifier = Modifier.size(60.dp)
                            )

                        }
                    }
                    IconButton(onClick = { actLazy = actLazyPlanet }) {
                        if (actLazy == actLazyPlanet) {
                            Icon(
                                painterResource(id = R.drawable.planet), contentDescription = null,
                                modifier = Modifier.size(60.dp),
                                tint = Purple40
                            )
                        } else {
                            Icon(
                                painterResource(id = R.drawable.planet), contentDescription = null,
                                modifier = Modifier.size(60.dp)
                            )
                        }
                    }

                    IconButton(onClick = { actLazy = actLazyStartShip }) {
                        if (actLazy == actLazyStartShip) {

                            Icon(
                                painterResource(id = R.drawable.air), contentDescription = null,
                                modifier = Modifier.size(60.dp),
                                tint = Purple40
                            )
                        } else {
                            Icon(
                                painterResource(id = R.drawable.air),
                                modifier = Modifier.size(60.dp),
                                contentDescription = null
                            )

                        }
                    }
                }
            }
        }
    ) {

        LazyColumn(Modifier.fillMaxSize()) {
            when (actLazy) {

                actPeople -> {
                    items(listperson.value!!) {
                        if (it.isFavorites) {
                            ItemPerson(it, viewModel)
                        }
                    }
                }

                actPlanet -> {
                    items(listPlanet.value!!) {
                        if (it.isFavorites) {
                            ItemPlanet(it, viewModel)
                        }
                    }
                }

                actStartShip -> {
                    items(liststartship.value!!) {
                        if (it.isFavorites) {
                            ItemStarship(it, viewModel)
                        }
                    }

                }


            }
        }
    }
}
