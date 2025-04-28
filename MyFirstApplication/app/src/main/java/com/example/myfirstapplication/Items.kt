package com.example.myfirstapplication

data class Items(
    val title:String,
    val text: String,
    val type:ItemType
)

enum class ItemType{
    TEXT,
    VIDEO,
    IMAGE
}