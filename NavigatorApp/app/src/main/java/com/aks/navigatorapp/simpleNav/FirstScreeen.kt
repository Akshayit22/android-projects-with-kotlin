package com.aks.navigatorapp.simpleNav

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FirstScreen(modifier: Modifier = Modifier, navigationToSecondScreen:(name: String, age: String)->Unit) {
    val name = remember { mutableStateOf("") }
    val age = remember { mutableStateOf("") }

    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "This is first screen",
            fontSize = 24.sp
        )
        Spacer(modifier = modifier.height(8.dp))

        OutlinedTextField(
            value = name.value,
            onValueChange = {
                name.value = it
            },
            label = { Text("Enter Name") }
        )
        Spacer(modifier = modifier.height(8.dp))


        OutlinedTextField(
            value = age.value,
            onValueChange = { age.value = it },
            label = { Text("Enter Age") }
        )

        Spacer(modifier = modifier.height(16.dp))

        Button(
            onClick = {navigationToSecondScreen(name.value, age.value)}
        ) {
            Text(
                text = "Click here to go to the next screen",
            )
        }
    }
}