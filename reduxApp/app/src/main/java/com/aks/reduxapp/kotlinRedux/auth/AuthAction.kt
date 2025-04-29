package com.aks.reduxapp.kotlinRedux.auth

sealed class AuthAction {
    data class Login(val username: String) : AuthAction()
    object Logout : AuthAction()
}