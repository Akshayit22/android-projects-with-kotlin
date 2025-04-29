package com.aks.reduxapp.simpleRedux

fun counterReducer(state:CounterState, action:CounterAction):CounterState{
    return when(action){
        is CounterAction.Increment -> state.copy(count = state.count + 1)
        is CounterAction.Decrement -> state.copy(count = state.count - 1)
    }
}