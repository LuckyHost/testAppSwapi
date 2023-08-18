package com.example.testapp.present

import android.os.*
import androidx.activity.*
import androidx.activity.compose.*
import androidx.navigation.compose.*
import com.example.testapp.present.Screens.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel:ViewModel by viewModels()

        viewModel.gelLoadAllData




        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "Splash") {
                composable("Home") {
                    Home(viewModel,navController)
                }

                composable("Splash") {
                    Splash(navController,viewModel)
                }

                composable("Favorite") {
                    Favorite(viewModel)
                }

                }
            }


        }
    }
