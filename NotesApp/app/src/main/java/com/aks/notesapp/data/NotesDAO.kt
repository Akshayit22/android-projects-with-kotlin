package com.aks.notesapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class NotesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addNote(notesEntity: Notes)

    @Query("Select * from `notes-table`")
    abstract fun getAllNotes(): Flow<List<Notes>>

    @Update
    abstract suspend fun updateNote(notesEntity: Notes)

    @Delete
    abstract suspend fun deleteNote(notesEntity: Notes)

    @Query("Select * from `notes-table` where id=:id")
    abstract fun getNoteById(id: Long): Flow<Notes>
}
