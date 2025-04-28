package com.aks.userlist.ui.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aks.userlist.data.model.User
import com.aks.userlist.navigation.Screen
import com.google.gson.Gson

@Composable
fun UserItem(user: User, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                val userJson = Gson().toJson(user)
                navController.navigate("${Screen.Details.route}/$userJson")
            }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${user.name}")
            Text(text = "Username: ${user.username}")
            Text(text = "Email: ${user.email}")
        }
    }
}