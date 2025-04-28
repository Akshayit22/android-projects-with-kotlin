package com.aks.navigatorapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.aks.navigatorapp.nestedNav.NestedScreen

@Composable
fun NestedNav(){
    val navController = rememberNavController()
    NavHost( navController , startDestination = NestedScreen.AuthRoute.route){

        authGraph(navController)

        //////////////////////////

        appGraph(navController)

    }
}