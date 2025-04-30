package com.aks.reduxapp.simpleRedux

import com.aks.reduxapp.kotlinRedux.AppState
import okhttp3.Dispatcher
import org.reduxkotlin.Middleware
import org.reduxkotlin.Reducer
import org.reduxkotlin.Thunk
import org.reduxkotlin.ThunkMiddleware
import org.reduxkotlin.middleware

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


val loggerMiddleware = middleware<CounterState> { store, next, action ->
    val result = next(action)
    println("next state: ${store.state}")
    result
}


fun createThunkMiddleware(): ThunkMiddleware<CounterState> =
    { store ->
        { next ->
            { action ->
                println("next Thunk: ${store.state}")
                if (action is Function<*>) {
                    @Suppress("UNCHECKED_CAST")
                    val thunk = action as Thunk<CounterState>
                    thunk(store.dispatch, store.getState, null)
                    println("next stateT: ${store.state}")
                } else {
                    next(action)
                }
            }
        }
    }