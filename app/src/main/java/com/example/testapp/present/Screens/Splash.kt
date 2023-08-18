package com.example.testapp.present.Screens

import android.util.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*
import androidx.navigation.*
import com.example.testapp.present.*
import com.example.testapp.present.theme.*
import kotlinx.coroutines.*

@Composable
fun Splash(navController: NavController, myViewModel: ViewModel) {


    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(Pink40),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    )
    {

        Text(
            text = "Loading...",
            color = Color.White,
            fontSize = 25.sp
        )
        LinearProgressIndicator(
            modifier = Modifier
                .width(200.dp)
                .height(4.dp),
            color = PurpleGrey40,
        )

        LaunchedEffect(true) {

            myViewModel.isLoadFile.collect {
                if (it) {
                    Log.d("MyLog", "Splash.kt. Splash: $it")
                    delay(1000)
                    navController.popBackStack()
                    navController.navigate("Home")
                }
            }


        }

    }
}