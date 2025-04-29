package com.aks.reduxapp.kotlinRedux.screens

import com.aks.reduxapp.kotlinRedux.AppState
import com.aks.reduxapp.kotlinRedux.appReducer
import com.aks.reduxapp.kotlinRedux.loggerMiddleware
import com.aks.reduxapp.kotlinRedux.loggerMiddleware2
import org.reduxkotlin.applyMiddleware
import org.reduxkotlin.createThreadSafeStore


object StoreProvider {
    val store = createThreadSafeStore(
        reducer = appReducer,
        preloadedState = AppState(),
        enhancer = applyMiddleware(loggerMiddleware,loggerMiddleware2)
    )
}