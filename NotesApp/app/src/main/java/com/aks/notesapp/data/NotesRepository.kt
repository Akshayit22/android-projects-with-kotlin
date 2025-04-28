package com.aks.notesapp.data

import kotlinx.coroutines.flow.Flow

class NotesRepository(private val notesDAO: NotesDAO) {

    suspend fun addNote(notesEntity: Notes){
        notesDAO.addNote(notesEntity)
    }

    fun getNotes():Flow<List<Notes>> = notesDAO.getAllNotes()

    fun getNoteById(id:Long): Flow<Notes> {
        return notesDAO.getNoteById(id)
    }

    suspend fun updateNote(notesEntity: Notes){
        notesDAO.updateNote(notesEntity)
    }

    suspend fun deleteNote(notesEntity: Notes){
        notesDAO.deleteNote(notesEntity)
    }

}