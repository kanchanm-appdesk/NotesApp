package com.example.notesapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp.NoteApplication
import com.example.notesapp.database.Notes
import com.example.notesapp.databinding.FragmentAddOrEditNotesBinding
import com.example.notesapp.viewModel.NotesViewModel
import com.example.notesapp.viewModel.NotesViewModelFactory
import kotlinx.coroutines.launch

class AddorEditNotes : Fragment(){

    private lateinit var binding: FragmentAddOrEditNotesBinding
    private val args: AddorEditNotesArgs by navArgs()
    lateinit var viewModel: NotesViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddOrEditNotesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this,
            NotesViewModelFactory((context?.applicationContext as NoteApplication).repository)
        )[NotesViewModel::class.java]
        binding.saveBtn.setOnClickListener {
            insertNote()
        }
        // fetching data from database to view

        if (args.noteId != -1L) {
            setOldNoteData(args.noteId)
        }
    }

    private fun insertNote() {
        val title = binding.addTitleEt.text.toString()
        val description = binding.addDescriptionEt.text.toString()
        if (title.isNotBlank() && description.isNotBlank()) {
            val note = if (args.noteId == -1L) {
                Notes(title, description)
            } else {
                Notes(title, description, args.noteId)
            }
            viewModel.addNotes(note)
            Toast.makeText(context, "Note Is Saved Successfully", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        } else {
            Toast.makeText(context, "All fields are mandatory", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setOldNoteData(noteId: Long) {
        lifecycleScope.launch {
            val note = viewModel.getNoteId(noteId)
            binding.addTitleEt.setText(note.title)
            binding.addDescriptionEt.setText(note.description)
        }
    }

}