package com.example.notesapp

import android.app.Application
import com.example.notesapp.database.NotesDatabase
import com.example.notesapp.database.NotesRepository


class NoteApplication : Application() {
    val dataBase by lazy { NotesDatabase.getDatabase(this) }
    val repository by lazy { NotesRepository(dataBase.getNotesDao()) }
}