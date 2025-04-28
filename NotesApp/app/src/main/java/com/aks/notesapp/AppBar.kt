package com.aks.notesapp

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppBarView(
    title:String,
    OnBackNavClick: ()-> Unit = {}
){
    Surface(
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 3.dp,
        color = colorResource(R.color.app_bar_color),
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {

        val navigationIcon:(@Composable () -> Unit)? = {
            if(!title.contains("Notes App")){
                IconButton(
                    onClick = {OnBackNavClick()}
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )
                }
            } else{
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )
                }
            }

        }

        TopAppBar(
            title = {
                Text(
                    text = title,
                    color = colorResource(R.color.white),
                    fontSize = 18.sp
                )
            },
            navigationIcon = navigationIcon,
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            modifier = Modifier.fillMaxWidth().padding(0.dp)
        )
    }
}