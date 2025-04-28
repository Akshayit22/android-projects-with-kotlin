package com.aks.navigatorapp.screens

sealed class Screen(val route:String){
    object ScreenA:Screen("ScreenA")
    object ScreenB:Screen("ScreenB")
    object ScreenC:Screen("ScreenC")
}