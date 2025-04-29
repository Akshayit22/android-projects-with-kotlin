package com.aks.reduxapp.kotlinRedux.counter

import org.reduxkotlin.Reducer

val counterReducer: Reducer<CounterState> = { state, action ->
    when (action) {
        is CounterAction.Increment -> state.copy(count = state.count + 1)
        is CounterAction.AddBy -> state.copy(count = state.count + action.value)
        is CounterAction.Decrement -> state.copy(count = state.count - 1)
        is CounterAction.Reset -> state.copy(count = 0)
        else -> state
    }
}