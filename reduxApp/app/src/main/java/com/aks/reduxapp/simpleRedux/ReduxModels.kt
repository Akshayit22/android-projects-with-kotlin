package com.aks.reduxapp.simpleRedux

data class CounterState(
    val count: Int = 0
)

sealed class CounterAction{
    object Increment: CounterAction()
    object Decrement: CounterAction()
}