package com.aks.notesapp

sealed class Screen (val route:String) {
    object Home:Screen("home")
    object Add:Screen("add")
}