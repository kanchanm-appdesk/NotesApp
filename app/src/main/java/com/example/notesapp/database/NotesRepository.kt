package com.example.notesapp.database

import androidx.lifecycle.LiveData

class NotesRepository(private val notesDao: NotesDao) {

    val allNotes: LiveData<List<Notes>> = notesDao.getAllNotes()

    suspend fun insertNote(notes: Notes)
    {
        notesDao.insertNotes(notes)
    }

    suspend fun updateNote(notes: Notes){
        notesDao.updateNotes(notes)
    }
    suspend fun deleteNote(notes: Notes){
        notesDao.deleteNotes(notes)
    }

    suspend fun getNoteId(noteId: Long): Notes {
        return notesDao.getNoteId(noteId)
    }
}