package com.aks.reduxapp.simpleRedux

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
/*
class CounterViewModel : ViewModel(){

    private val _state = mutableStateOf(CounterState())
    val state: State<CounterState> = _state

    fun dispatch(action: CounterAction){
        _state.value = counterReducer(_state.value,action)
    }


    private val _state = mutableStateOf(AppState())
    val state: State<AppState> = _state

    fun dispatch(action: AppAction){
        _state.value = appReducer(_state.value,action)
    }

}

 */