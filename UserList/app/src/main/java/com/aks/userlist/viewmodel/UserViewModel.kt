package com.aks.userlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aks.userlist.data.model.User
import com.aks.userlist.data.repository.UserRepository
import com.aks.userlist.utils.NetworkResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {

    private val repository = UserRepository()

    private val _userListState = MutableStateFlow<NetworkResponse<List<User>>>(NetworkResponse.Loading)
    val userListState:StateFlow<NetworkResponse<List<User>>> = _userListState

    init {
        fetchUsers()
    }

    private fun fetchUsers(){
        viewModelScope.launch {
            _userListState.value = NetworkResponse.Loading
            _userListState.value = repository.getUsers()
        }
    }

}