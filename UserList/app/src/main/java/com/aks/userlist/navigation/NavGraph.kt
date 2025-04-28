package com.aks.userlist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aks.userlist.data.model.User
import com.aks.userlist.ui.screens.UserDetailScreen
import com.aks.userlist.ui.screens.UserListScreen
import com.google.gson.Gson

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            UserListScreen(navController)
        }

        composable(
            route = "${Screen.Details.route}/{user}",
            arguments = listOf(navArgument("user") { type = NavType.StringType })
        ) { backStackEntry ->
            val jsonUser = backStackEntry.arguments?.getString("user")
            val user = Gson().fromJson(jsonUser, User::class.java)
            UserDetailScreen(user, navController)
        }
    }

}