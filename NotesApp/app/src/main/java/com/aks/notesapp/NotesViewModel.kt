package com.aks.notesapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aks.notesapp.data.Graph
import com.aks.notesapp.data.Notes
import com.aks.notesapp.data.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NotesViewModel(
    private val notesRepository: NotesRepository = Graph.notesRepository
) : ViewModel() {

    var noteTitleState by mutableStateOf("")
    var notedescriptionState by mutableStateOf("")

    fun onNoteTitleChanged(newString: String) {
        noteTitleState = newString
    }

    fun onNoteDescriptionChanged(newString: String) {
        notedescriptionState = newString
    }

    // Direct assignment — getNotes() returns a Flow, no coroutine needed
    val getAllNotes: Flow<List<Notes>> = notesRepository.getNotes()

    fun getNoteById(id: Long): Flow<Notes> = notesRepository.getNoteById(id)

    fun addNote(note: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.addNote(note)
        }
    }

    fun updateNote(note: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.updateNote(note)
        }
    }

    fun deleteNote(note: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.deleteNote(note)
        }
    }
}
