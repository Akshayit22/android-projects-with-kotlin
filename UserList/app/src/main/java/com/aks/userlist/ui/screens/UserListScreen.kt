package com.aks.userlist.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aks.userlist.data.model.User
import com.aks.userlist.utils.NetworkResponse
import com.aks.userlist.viewmodel.UserViewModel
import androidx.compose.material3.Text
import androidx.navigation.NavController

@Composable
fun UserListScreen(navController: NavController) {
    val viewModel: UserViewModel = viewModel()
    val state by viewModel.userListState.collectAsState()

    when (state) {
        is NetworkResponse.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is NetworkResponse.Success -> {
            val users = (state as NetworkResponse.Success<List<User>>).data
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
            ) {
                items(users) { user ->
                    UserItem(user = user, navController = navController)
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }

        is NetworkResponse.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = (state as NetworkResponse.Error).message)
            }
        }
    }
}

