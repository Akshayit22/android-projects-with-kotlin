package com.aks.weatherapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
//    https://api.weatherapi.com/v1/current.json?key=2a8416cc54f74ce0be1140718242512&q=Nasik&aqi=no
    private const val baseUrl = "https://api.weatherapi.com";

    private fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    val weatherApi : WeatherApi = getInstance().create(WeatherApi::class.java)

}