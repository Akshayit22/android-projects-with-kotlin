package com.aks.reduxapp.kotlinRedux.screens

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.aks.reduxapp.kotlinRedux.AppAction
import com.aks.reduxapp.kotlinRedux.AppState

class AuthCounterViewModel : ViewModel() {

    private val store = StoreProvider.store

    private val _state = mutableStateOf(store.state)
    val state: State<AppState> = _state

    init {
        store.subscribe {
            _state.value = store.state
        }
    }

    fun dispatch(action: AppAction) {
        store.dispatch(action)
    }
}