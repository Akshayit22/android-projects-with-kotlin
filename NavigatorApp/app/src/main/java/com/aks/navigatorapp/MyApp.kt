package com.aks.navigatorapp

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aks.navigatorapp.simpleNav.FirstScreen
import com.aks.navigatorapp.simpleNav.Screens
import com.aks.navigatorapp.simpleNav.SecondScreen


@Composable
fun MyApp(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.FirstScreen.route) {

        composable(Screens.FirstScreen.route) {
            FirstScreen { name, age ->
                val encodedName = Uri.encode(name)
                navController.navigate("${Screens.SecondScreen.route}?name=$encodedName&age=${Uri.encode(age)}")
            }
        }

        composable(
            route = "${Screens.SecondScreen.route}?name={name}&age={age}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = "No name"
                    nullable = true
                } ,
                navArgument("age") {
                    type = NavType.StringType
                    defaultValue = "0"
                    nullable = true
                }
            )
        ) {
            val rawName = it.arguments?.getString("name") ?: ""
            val name = if (rawName.isBlank()) "User" else rawName

            val rawAge = it.arguments?.getString("age") ?: ""
            val age = if (rawAge.isBlank()) "0" else rawAge

            SecondScreen(name,age) {
                navController.popBackStack()
            }
        }
    }
}

