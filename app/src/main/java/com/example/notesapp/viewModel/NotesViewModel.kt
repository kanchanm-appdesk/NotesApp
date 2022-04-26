package com.example.notesapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.database.Notes
import com.example.notesapp.database.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(val notesRepository: NotesRepository): ViewModel() {

    val allNotes = notesRepository.allNotes

    fun addNotes(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.insertNote(notes)
        }
    }

    fun deleteNotes(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.deleteNote(notes)
        }
    }
    fun updateNotes(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.deleteNote(notes)
        }
    }
    suspend fun getNoteId(noteId: Long): Notes {
        return notesRepository.getNoteId(noteId)
    }


}