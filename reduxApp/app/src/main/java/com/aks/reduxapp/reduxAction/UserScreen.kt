package com.aks.reduxapp.reduxAction

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun UserListScreen(viewModel: UserViewModel = UserViewModel()) {
    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> Text("Loading...")
        state.error != null -> Text("Error: ${state.error}")
        else -> {
            LazyColumn {
                items(state.users) { user ->
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("Id : ${user.id}")
                        Text(text = user.name, fontWeight = FontWeight.Bold)
                        Text(text = user.email)
                    }
                }
            }
        }
    }
}