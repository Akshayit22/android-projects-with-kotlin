package com.aks.navigatorapp.nestedNav

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Login(navController: NavController) {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Login Screen", fontSize = 32.sp)
        Spacer(modifier = Modifier.height(65.dp))

        Button(onClick = {
            navController.navigate(NestedScreen.Home.route)
        }) {
            Text(
                text = "After Login ( HOME )",
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(65.dp))

        Button(onClick = {
            navController.navigate(NestedScreen.ForgotPass.route)
        }) {
            Text(
                text = "Forgot Password",
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(65.dp))

        Button(onClick = {
            navController.navigate(NestedScreen.Register.route)
        }) {
            Text(
                text = "Register",
                fontSize = 20.sp
            )
        }
    }
}