package com.aks.navigatorapp.screens

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
import androidx.navigation.NavHostController


@Composable
fun ScreenB(navController: NavHostController) {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Screen B", fontSize = 32.sp)
        Spacer(modifier = Modifier.height(65.dp))

        Button(onClick = {
            navController.navigate(Screen.ScreenC.route)
        }) {
            Text(
                text = "Go to screen C",
                fontSize = 20.sp
            )
        }
    }
}