package com.example.testapp.present.Screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import com.example.testapp.domian.Room.DataClass.Peoples.*
import com.example.testapp.present.*

//@Preview(showBackground = true)
@Composable
fun ItemPerson(people: ResultPeople, viewModel: ViewModel) {
    var textName by remember { mutableStateOf("Luke Skywalker") }
    var textGender by remember { mutableStateOf("male") }
    var textStartShips by remember { mutableStateOf("6") }
    var startRot by remember { mutableStateOf(false)    }
    val animRot by animateFloatAsState(targetValue = if(startRot) 360f else 0f, animationSpec = tween(500),
        label = ""
    )




    textName = people.name
    textGender = people.gender
    textStartShips = people.starships.size.toString()





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
                            text = "Gender:"
                        )
                        Text(
                            modifier = Modifier.padding(6.dp),
                            fontWeight = FontWeight.ExtraBold,
                            text = textGender
                        )
                    }



                    Row {
                        Text(
                            modifier = Modifier.padding(4.dp),
                            text = "Starships:"
                        )
                        Text(
                            modifier = Modifier.padding(4.dp),
                            fontWeight = FontWeight.ExtraBold,
                            text = textStartShips
                        )
                    }

                   /* Row() {
                        Text(
                            modifier = Modifier.padding(4.dp),
                            text = "Film:"
                        )
                        Text(
                            modifier = Modifier.padding(4.dp),
                            fontSize = 4.sp,
                            fontWeight = FontWeight.ExtraBold,
                            text = textFilm.value
                        )
                    }*/

                }


            }


            IconButton(

                onClick = { viewModel.updateFavoritePeople(people); startRot=!startRot}
            )
            {
                if (!people.isFavorites) {
                    Icon(Icons.Default.FavoriteBorder, null
                    , modifier =Modifier.rotate(animRot))
                } else {
                    Icon(Icons.Default.FavoriteBorder, null, tint = Color.Red)
                }

            }


        }
    }
}