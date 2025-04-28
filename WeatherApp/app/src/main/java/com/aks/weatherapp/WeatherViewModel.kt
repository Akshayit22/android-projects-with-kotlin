package com.aks.weatherapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aks.weatherapp.api.Constant
import com.aks.weatherapp.api.NetworkResponse
import com.aks.weatherapp.api.RetrofitInstance
import com.aks.weatherapp.api.WeatherModel
import kotlinx.coroutines.launch


class WeatherViewModel : ViewModel() {

    private val weatherApi = RetrofitInstance.weatherApi

    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()

    val weatherResult : LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    private val _city = mutableStateOf("pune")
    val city: MutableState<String> = _city

    fun setCity(value: String) {
        _city.value = value
    }


    fun getData(city: String) {
        _weatherResult.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val response = weatherApi.getWeather(Constant.apiKey, city)
                if (response.isSuccessful) {
                    response.body()?.let{
                        _weatherResult.value = NetworkResponse.Success(it)
                    }
                } else {
                    _weatherResult.value = NetworkResponse.Error("Failed to load data.")
                }
            }
            catch (e:Exception){
                _weatherResult.value = NetworkResponse.Error("Failed to load data.")
            }

        }

    }

}