package com.aks.notesapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes-table")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "note-title")
    val title: String = "",
    @ColumnInfo(name = "note-desc")
    val description: String = ""
)

object DummyData {
    val noteList = listOf(
        Notes(
            id = 1L,
            title = "Grocery List",
            description = "Buy milk, eggs, bread, and vegetables."
        ),
        Notes(
            id = 2L,
            title = "Meeting Notes",
            description = "Discuss project timeline and deliverables with team."
        ),
        Notes(
            id = 3L,
            title = "Travel Plan",
            description = "Visit Manali in December. Book hotel and train tickets."
        ),
        Notes(
            id = 4L,
            title = "Workout Routine",
            description = "Monday - Chest, Tuesday - Back, Wednesday - Legs."
        ),
        Notes(
            id = 5L,
            title = "Books to Read",
            description = "Atomic Habits, Deep Work, The Alchemist."
        )
    )
}