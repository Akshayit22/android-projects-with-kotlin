package com.aks.weatherapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.aks.weatherapp.api.NetworkResponse
import com.aks.weatherapp.api.WeatherModel

@Composable
fun WeatherPage(viewModel: WeatherViewModel) {

    val city = viewModel.city.value
    val weatherResult = viewModel.weatherResult.observeAsState()
    val keyBoardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(
                value = city,
                onValueChange = {
                    viewModel.setCity(it)
                },
                label = {
                    Text(text = "Search for any location ...")
                },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = {
                    if(viewModel.city.value !== ""){
                        viewModel.getData(viewModel.city.value)
                    }
                    keyBoardController?.hide()
                },
                modifier = Modifier.align(alignment = Alignment.CenterVertically).padding(top = 8.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Button",
                    modifier = Modifier.size(40.dp),
                )
            }
        }

        when (val result = weatherResult.value) {
            is NetworkResponse.Error -> {
                Text(text = result.message)
            }

            NetworkResponse.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxHeight(0.2f)
                    )
                }
            }

            is NetworkResponse.Success -> {
                WeatherDetails(result.data)
            }

            null -> {}
        }
    }
}

@Composable
fun WeatherDetails(data: WeatherModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location icon",
                modifier = Modifier.size(32.dp),
            )
            Text(
                text = data.location.name,
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = data.location.country,
                fontSize = 24.sp,
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "${data.current.temp_c} °c",
            fontSize = 56.sp,

            textAlign = TextAlign.Center
        )

        AsyncImage(
            model = "https:${data.current.condition.icon}".replace("64x64","128x128"),
            modifier = Modifier.size(160.dp),
            contentDescription = "Condition Icon",
        )

        Text(
            text = data.current.condition.text,
            fontSize = 20.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card {
            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    WeatherKeyValue("Humidity", data.current.humidity)
                    WeatherKeyValue("Wind Speed", data.current.wind_kph + " km/h")
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    WeatherKeyValue("Wind Direction", data.current.wind_dir)
                    WeatherKeyValue("Pressure", data.current.pressure_mb + " hPa")
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    WeatherKeyValue("Local Time", data.location.localtime.split(" ")[1])
                    WeatherKeyValue("Local Date", data.location.localtime.split(" ")[0])
                }
            }
        }

    }
}

@Composable
fun WeatherKeyValue(key:String, value:String){
    Column (
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
            )

        Text(
            text = key,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
            )
    }
}