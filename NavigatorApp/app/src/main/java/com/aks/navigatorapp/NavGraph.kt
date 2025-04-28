package com.aks.navigatorapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aks.navigatorapp.screens.Screen
import com.aks.navigatorapp.screens.ScreenA
import com.aks.navigatorapp.screens.ScreenB
import com.aks.navigatorapp.screens.ScreenC

@Composable
fun Nav(){
    val navController = rememberNavController()
    NavHost( navController , startDestination = Screen.ScreenA.route){
        composable(route = Screen.ScreenA.route){
            ScreenA(navController)
        }

        composable(route = Screen.ScreenB.route){
            ScreenB(navController)
        }

        composable(route = Screen.ScreenC.route){
            ScreenC(navController)
        }
    }
}