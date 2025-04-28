package com.aks.navigatorapp.simpleNav

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SecondScreen(name:String, age: String, modifier: Modifier = Modifier, navigationToFirstScreen:()->Unit) {

    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome $name and  $age to Second Screen",
            fontSize = 24.sp
        )
        Spacer(modifier = modifier.height(8.dp))

        Button(
            onClick = {
                navigationToFirstScreen()
            }
        ) {
            Text(
                text = "Click here to go to the First screen",
            )
        }
    }
}