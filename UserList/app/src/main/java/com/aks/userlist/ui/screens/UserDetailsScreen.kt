package com.aks.userlist.ui.screens

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aks.userlist.data.model.User
import androidx.core.net.toUri

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(user: User, navController: NavController) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("User Details") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            tint = MaterialTheme.colorScheme.onBackground,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Text(text = "ID: ${user.id}", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Name: ${user.name}")
            Text(text = "Username: ${user.username}")

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Email: ${user.email}",
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                        data = "mailto:${user.email}".toUri()
                    }
                    context.startActivity(emailIntent)
                }
            )

            Text(
                text = "Website: ${user.website}",
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    val websiteIntent = Intent(Intent.ACTION_VIEW).apply {
                        data = "http://${user.website}".toUri()
                    }
                    context.startActivity(websiteIntent)
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Phone: ${user.phone}")

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Phone: ${user.phone}")
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Address:")
            Text(text = "  Street: ${user.address.street}")
            Text(text = "  Suite: ${user.address.suite}")
            Text(text = "  City: ${user.address.city}")
            Text(text = "  Zipcode: ${user.address.zipcode}")
            Text(text = "  Geo: Lat(${user.address.geo.lat}), Lng(${user.address.geo.lng})")
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Company:")
            Text(text = "  Name: ${user.company.name}")
            Text(text = "  Catch Phrase: ${user.company.catchPhrase}")
            Text(text = "  Business: ${user.company.bs}")
        }
    }
}