package com.aks.notesapp

import android.app.Application
import com.aks.notesapp.data.Graph

class NotesApp:Application() {
    override fun onCreate(){
        super.onCreate()
        Graph.provide(this)
    }
}