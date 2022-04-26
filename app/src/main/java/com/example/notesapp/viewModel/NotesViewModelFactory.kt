package com.example.notesapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.database.NotesRepository

class NotesViewModelFactory(private val notesRepository: NotesRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return NotesViewModel(notesRepository) as T
    }

}