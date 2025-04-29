package com.aks.reduxapp.kotlinRedux.counter

sealed class CounterAction {
    object Increment : CounterAction()
    data class AddBy(val value: Int) : CounterAction()
    object Decrement : CounterAction()
    object Reset: CounterAction()
}