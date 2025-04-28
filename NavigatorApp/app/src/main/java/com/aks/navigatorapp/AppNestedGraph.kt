package com.aks.navigatorapp

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.aks.navigatorapp.nestedNav.Home
import com.aks.navigatorapp.nestedNav.NestedScreen
import com.aks.navigatorapp.nestedNav.ScreenA
import com.aks.navigatorapp.nestedNav.ScreenB

fun NavGraphBuilder.appGraph(navController:NavController){
    navigation(startDestination = NestedScreen.Home.route, route= NestedScreen.AppRoute.route){

        composable(route = NestedScreen.Home.route){
            Home(navController)
        }
        composable(route = NestedScreen.ScreenA.route){
            ScreenA(navController)
        }
        composable(route = NestedScreen.ScreenB.route){
            ScreenB(navController)
        }

    }
}