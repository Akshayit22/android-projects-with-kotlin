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
fun Home(navController: NavController) {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Home Screen", fontSize = 32.sp)
        Spacer(modifier = Modifier.height(65.dp))

        Button(onClick = {
            navController.navigate(NestedScreen.ScreenA.route)
        }) {
            Text(
                text = "Go to screen A",
                fontSize = 20.sp
            )
        }

        Button(onClick = {
            navController.navigate(NestedScreen.ScreenB.route)
        }) {
            Text(
                text = "Go to screen B",
                fontSize = 20.sp
            )
        }
    }
}