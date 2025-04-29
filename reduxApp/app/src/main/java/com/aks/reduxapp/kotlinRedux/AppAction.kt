package com.aks.reduxapp.kotlinRedux

import com.aks.reduxapp.kotlinRedux.auth.AuthAction
import com.aks.reduxapp.kotlinRedux.counter.CounterAction

sealed class AppAction {
    data class Auth(val action: AuthAction) : AppAction()
    data class Counter(val action: CounterAction) : AppAction()
}