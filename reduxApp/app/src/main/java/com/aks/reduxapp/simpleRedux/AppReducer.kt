package com.aks.reduxapp.simpleRedux

data class AppState(
    val counterState: CounterState = CounterState()
)

sealed class AppAction {
    data class Counter(val action: CounterAction) : AppAction()
}

fun appReducer(state: AppState, action: AppAction): AppState {
    return when (action) {
        is AppAction.Counter -> {
            state.copy(
                counterState = counterReducer(state.counterState, action.action)
            )
        }
    }
}