package com.aks.navigatorapp.nestedNav

sealed class NestedScreen(val route:String){
    object Home:NestedScreen("Home")
    object Login:NestedScreen("Login")
    object ForgotPass:NestedScreen("ForgotPass")
    object Register:NestedScreen("Register")
    object ScreenA:NestedScreen("ScreenA")
    object ScreenB:NestedScreen("ScreenB")

    object AuthRoute:NestedScreen("AuthRoute")
    object AppRoute:NestedScreen("AppRoute")
}
