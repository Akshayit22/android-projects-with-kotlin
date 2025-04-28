package com.aks.notesapp.data

import android.content.Context
import androidx.room.Room

object Graph {
    lateinit var database: NotesDatabase

    val notesRepository by lazy{
        NotesRepository(notesDAO = database.notesDao())
    }

    fun provide(context:Context){
        database = Room.databaseBuilder(context,NotesDatabase::class.java, "notes.db").build()
    }
}