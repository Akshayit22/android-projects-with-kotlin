package com.aks.reduxapp.kotlinRedux

import com.aks.reduxapp.kotlinRedux.auth.AuthState
import com.aks.reduxapp.kotlinRedux.counter.CounterState

data class AppState(
    val auth: AuthState = AuthState(),
    val counter: CounterState = CounterState()
)