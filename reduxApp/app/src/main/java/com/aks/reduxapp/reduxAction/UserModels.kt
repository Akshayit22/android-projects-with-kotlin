package com.aks.reduxapp.reduxAction

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val name: String,
    val email: String
)

data class UserState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: String? = null
)

sealed class UserActions {
    object FetchUsersStarted : UserActions()
    data class FetchUsersSuccess(val users: List<User>) : UserActions()
    data class FetchUsersFailure(val error: String) : UserActions()
}