package com.aks.userlist.data.repository

import com.aks.userlist.data.model.User
import com.aks.userlist.data.remote.RetrofitInstance
import com.aks.userlist.utils.NetworkResponse

class UserRepository {
    private val apiService = RetrofitInstance.api

    suspend fun getUsers(): NetworkResponse<List<User>>{
        return try {
            val users = apiService.getUsers()
            NetworkResponse.Success(users)
        } catch (e: Exception) {
            NetworkResponse.Error(e.localizedMessage ?: "An unexpected error occurred")
        }
    }
}