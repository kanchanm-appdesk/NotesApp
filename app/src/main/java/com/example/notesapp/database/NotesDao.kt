package com.example.notesapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notesapp.database.Notes

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNotes(notes: Notes)

    @Delete
    suspend fun deleteNotes(notes: Notes)

    @Update
    suspend fun updateNotes(notes: Notes)

    @Query("Select * from notes_table order by id ASC")
    fun getAllNotes() : LiveData<List<Notes>>

    @Query("SELECT * FROM notes_table where id =:noteId")
    suspend fun getNoteId(noteId: Long): Notes
}