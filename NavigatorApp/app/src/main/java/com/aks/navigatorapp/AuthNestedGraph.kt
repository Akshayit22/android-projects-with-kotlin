package com.aks.navigatorapp

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.aks.navigatorapp.nestedNav.ForgotPass
import com.aks.navigatorapp.nestedNav.Login
import com.aks.navigatorapp.nestedNav.NestedScreen
import com.aks.navigatorapp.nestedNav.Register

fun NavGraphBuilder.authGraph(navController: NavController){
    navigation(startDestination = NestedScreen.Login.route , route= NestedScreen.AuthRoute.route ){

        composable(route = NestedScreen.Login.route){
            Login(navController)
        }
        composable(route = NestedScreen.Register.route){
            Register(navController)
        }
        composable(route = NestedScreen.ForgotPass.route){
            ForgotPass(navController)
        }
    }
}