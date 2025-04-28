package com.aks.userlist.data.remote

import com.aks.userlist.data.model.User
import retrofit2.http.GET

interface ApiService{

    @GET("/users")
    suspend fun getUsers():List<User>
}