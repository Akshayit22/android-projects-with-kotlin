package com.aks.reduxapp.kotlinRedux.auth

data class AuthState(
    val isLoggedIn: Boolean = false,
    val username: String = ""
)
