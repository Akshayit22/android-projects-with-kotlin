package com.aks.reduxapp.kotlinRedux

import com.aks.reduxapp.kotlinRedux.auth.authReducer
import com.aks.reduxapp.kotlinRedux.counter.counterReducer
import com.aks.reduxapp.simpleRedux.CounterState
import org.reduxkotlin.Middleware
import org.reduxkotlin.Reducer

val appReducer: Reducer<AppState> = { state, action ->
    when (action) {
        is AppAction.Auth -> state.copy(auth = authReducer(state.auth, action.action))
        is AppAction.Counter -> state.copy(counter = counterReducer(state.counter, action.action))
        else -> state
    }
}

val loggerMiddleware: Middleware<AppState> = { store ->
    {next ->
        { action ->
            println("New State: loggerMiddleware 1 : ${store.state.counter.count}")
            next(action)
        }
    }
}

val loggerMiddleware2: Middleware<AppState> = { store ->
    {next ->
        { action ->
            println("New State: loggerMiddleware 2 : ${store.state.counter.count}")
            store.state.counter.count
            next(action)
        }
    }
}