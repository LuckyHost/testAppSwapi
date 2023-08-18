package com.example.testapp.present.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import com.example.testapp.domian.Room.DataClass.Starships.ResultStarShip
import com.example.testapp.domian.Room.DataClass.*
import com.example.testapp.present.*


@Composable
fun ItemStarship(starship: ResultStarShip, viewModel: ViewModel) {

    var textName by remember { mutableStateOf("Death Star")    }
    var textManufacturer by remember { mutableStateOf("Imperial Department of Military Research, Sienar Fleet Systems")}
    var textPassengers by remember { mutableStateOf("843,342")}


    textName = starship.name
    textManufacturer = starship.manufacturer
    textPassengers = starship.passengers


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
                    .fillMaxWidth(0.85f)
                /*.background(Color.Blue)*/,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ){
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
                            text = "Manufacturer:"
                        )
                        Text(
                            modifier = Modifier.padding(4.dp),
                            fontWeight = FontWeight.ExtraBold,
                            text = textManufacturer
                        )
                    }



                    Row {
                        Text(
                            modifier = Modifier.padding(4.dp),
                            text = "Passengers:"
                        )
                        Text(
                            modifier = Modifier.padding(4.dp),
                            fontWeight = FontWeight.ExtraBold,
                            text = textPassengers
                        )
                    }

                }



            }


            IconButton(

                onClick = { viewModel.updateFavoriteStarShipe(starship)}
            )
            {
                if (!starship.isFavorites) {
                    Icon(Icons.Default.FavoriteBorder, "Delete Item")
                } else {
                    Icon(Icons.Default.FavoriteBorder, "Delete Item", tint = Color.Red)
                }

            }
        }
    }
}