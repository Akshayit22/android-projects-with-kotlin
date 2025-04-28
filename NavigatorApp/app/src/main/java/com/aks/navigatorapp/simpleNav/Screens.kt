package com.aks.navigatorapp.simpleNav

sealed class Screens(val route:String){
    object FirstScreen: Screens("firstScreen")
    object SecondScreen: Screens("secondScreen")
}