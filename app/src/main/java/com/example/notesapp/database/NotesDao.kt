package com.example.notesapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notesapp.database.Notes

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: Notes)

    @Delete
    suspend fun deleteNotes(notes: Notes)

    @Query("Select * from notes_table")
    fun getAllNotes() : LiveData<List<Notes>>

    @Query("SELECT * FROM notes_table where id =:noteId")
    suspend fun getNoteId(noteId: Long): Notes
}