package com.example.testapp.present.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import com.example.testapp.domian.Room.DataClass.*
import com.example.testapp.domian.Room.DataClass.Planets.*
import com.example.testapp.present.*

//@Preview(showBackground = true)
@Composable
fun ItemPlanet(planet: ResultPlanet, viewModel: ViewModel) {
    var textName by remember { mutableStateOf("Luke Skywalker") }
    var diameter by remember { mutableStateOf("male") }
    var population by remember { mutableStateOf("6") }



    textName = planet.name
    diameter = planet.diameter
    population = planet.population



    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 6.dp, end = 6.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 15.dp
        )
    )
    {
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        )
        {
            Column(
                modifier = Modifier
                    .padding(9.dp)
                /*.background(Color.Blue)*/,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    text = textName,
                    fontSize = 30.sp,
                )

                Column(
                    modifier = Modifier
                        .padding(9.dp)
                    /*.background(Color.Blue)*/,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                )
                {


                    Row {
                        Text(
                            modifier = Modifier.padding(4.dp),
                            text = "Diameter:"
                        )
                        Text(
                            modifier = Modifier.padding(4.dp),
                            fontWeight = FontWeight.ExtraBold,
                            text = diameter
                        )
                    }



                    Row {
                        Text(
                            modifier = Modifier.padding(4.dp),
                            text = "Population:"
                        )
                        Text(
                            modifier = Modifier.padding(4.dp),
                            fontWeight = FontWeight.ExtraBold,
                            text = population
                        )
                    }

                }


            }


            IconButton(

                onClick = { viewModel.updateFavoritePlanet(planet)}
            )
            {
                if (!planet.isFavorites) {
                    Icon(Icons.Default.FavoriteBorder, null)
                } else {
                    Icon(Icons.Default.FavoriteBorder, null, tint = Color.Red)
                }

            }


        }
    }
}

