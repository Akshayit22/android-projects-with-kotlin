package com.aks.reduxapp.kotlinRedux.auth

import org.reduxkotlin.Reducer

val authReducer: Reducer<AuthState> = { state, action ->
    when (action) {
        is AuthAction.Login -> state.copy(isLoggedIn = true, username = action.username)
        is AuthAction.Logout -> state.copy(isLoggedIn = false, username = "")
        else -> state
    }
}