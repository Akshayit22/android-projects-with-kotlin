package com.aks.reduxapp.reduxAction

import androidx.lifecycle.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.reduxkotlin.Thunk
import org.reduxkotlin.applyMiddleware
import org.reduxkotlin.createThreadSafeStore
import org.reduxkotlin.createThunkMiddleware


fun userReducer(state: UserState = UserState(), action: Any): UserState {
    return when (action) {
        is UserActions.FetchUsersStarted -> state.copy(isLoading = true, error = null)
        is UserActions.FetchUsersSuccess -> state.copy(isLoading = false, users = action.users)
        is UserActions.FetchUsersFailure -> state.copy(isLoading = false, error = action.error)
        else -> state
    }
}

val client = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }
}

fun fetchUsersThunk(): Thunk<UserState> = { dispatch, _, _ ->
    val scope = CoroutineScope(Dispatchers.IO)

    dispatch(UserActions.FetchUsersStarted)

    scope.launch {
        try {
            val users: List<User> = client.get("https://jsonplaceholder.typicode.com/users").body()
            println("Error : ${users}")
            dispatch(UserActions.FetchUsersSuccess(users))
        } catch (e: Exception) {
            println("Error : ${e.localizedMessage}")
            dispatch(UserActions.FetchUsersFailure(e.localizedMessage ?: "Unknown error"))
        }
    }
}

object StoreProvider {
    val store = createThreadSafeStore(
        reducer = ::userReducer,
        preloadedState = UserState(),
        enhancer = applyMiddleware(createThunkMiddleware())
    )
}


class UserViewModel : ViewModel() {
    private val _state = MutableStateFlow(StoreProvider.store.state)
    val state = _state.asStateFlow()

    init {
        StoreProvider.store.subscribe {
            _state.value = StoreProvider.store.state
        }
        StoreProvider.store.dispatch(fetchUsersThunk())
    }
}