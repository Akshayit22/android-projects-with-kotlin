package com.aks.reduxapp.kotlinRedux.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aks.reduxapp.kotlinRedux.AppAction
import com.aks.reduxapp.kotlinRedux.auth.AuthAction
import com.aks.reduxapp.kotlinRedux.counter.CounterAction


@Composable
fun AuthCounter(viewModel: AuthCounterViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val state by viewModel.state

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Welcome: ${if (state.auth.isLoggedIn) state.auth.username else "Guest"}")

        if (!state.auth.isLoggedIn) {
            Button(onClick = {
                viewModel.dispatch(AppAction.Auth(AuthAction.Login("Akshay")))
                viewModel.dispatch(AppAction.Counter(CounterAction.Reset))
            }) {
                Text("Login")
            }
        } else {
            Button(onClick = {
                viewModel.dispatch(AppAction.Auth(AuthAction.Logout))
                viewModel.dispatch(AppAction.Counter(CounterAction.Reset))
            }) {
                Text("Logout")
            }
        }

        Spacer(Modifier.height(24.dp))

        Text("Counter: ${state.counter.count}", fontSize = 24.sp)

        Row {
            Button(onClick = { viewModel.dispatch(AppAction.Counter(CounterAction.Increment)) }) {
                Text("Increment")
            }
            Button(onClick = { viewModel.dispatch(AppAction.Counter(CounterAction.Decrement)) }) {
                Text("Decrement")
            }
            Button(onClick = { viewModel.dispatch(AppAction.Counter(CounterAction.AddBy(5))) }) {
                Text("Add 5")
            }
        }
    }
}