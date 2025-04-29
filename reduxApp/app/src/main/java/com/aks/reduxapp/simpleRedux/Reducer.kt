package com.aks.reduxapp.simpleRedux

import org.reduxkotlin.Middleware
import org.reduxkotlin.Reducer

/*
fun counterReducer(state:CounterState, action:CounterAction):CounterState{
    return when(action){
        is CounterAction.Increment -> state.copy(count = state.count + 1)
        is CounterAction.Decrement -> state.copy(count = state.count - 1)
    }
}
 */

val counterReducer: Reducer<CounterState> = { state, action ->
    when(action){
        is CounterAction.Increment -> state.copy(count = state.count + 1)
        is CounterAction.Decrement -> state.copy(count = state.count - 1)
        else -> state
    }
}


val loggerMiddleware: Middleware<CounterState> = { store ->
    {next ->
        { action ->
            println("New State: ${store.state}")
            next(action)
            println("New State: ${store.state}")
        }
    }
}