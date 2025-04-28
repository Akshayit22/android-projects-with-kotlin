package com.aks.userlist.navigation

sealed class Screen(val route:String){
    object Home:Screen("Home")
    object Details:Screen("details")
}
