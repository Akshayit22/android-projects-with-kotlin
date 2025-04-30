package com.aks.reduxapp.simpleRedux

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.reduxkotlin.Thunk
import org.reduxkotlin.applyMiddleware
import org.reduxkotlin.createThreadSafeStore

fun incrementAsync(): Thunk<CounterState> = { dispatch, getState, _ ->
    CoroutineScope(Dispatchers.Main).launch {
        delay(1000)
        println("Thunk: Dispatching increment")
        dispatch(CounterAction.Increment)
    }
}

object StoreHolder {
    val store = createThreadSafeStore(
        reducer = counterReducer,
        preloadedState = CounterState(),
        enhancer = applyMiddleware(createThunkMiddleware(), loggerMiddleware)
    )
}

@Composable
fun CounterScreen() {
    val store = StoreHolder.store
    val (state, setState) = remember { mutableStateOf(store.state) }

    LaunchedEffect(Unit) {
        store.subscribe {
            setState(store.state)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Count: ${state.count}", fontSize = 30.sp)
        Row {
            Button(onClick = { store.dispatch(incrementAsync()) }) {
                Text("Increment")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { store.dispatch(CounterAction.Decrement) }) {
                Text("Decrement")
            }
        }
    }
}