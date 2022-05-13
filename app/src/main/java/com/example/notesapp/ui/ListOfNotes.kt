package com.example.notesapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.NoteApplication
import com.example.notesapp.R
import com.example.notesapp.database.Notes
import com.example.notesapp.databinding.FragmentListOfNotesBinding
import com.example.notesapp.recylerView.RecyclerViewAdapter
import com.example.notesapp.viewModel.NotesViewModel
import com.example.notesapp.viewModel.NotesViewModelFactory


class ListOfNotes : Fragment() {

    private lateinit var binding: FragmentListOfNotesBinding
    private lateinit var viewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListOfNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            NotesViewModelFactory((context?.applicationContext as NoteApplication).repository)
        )[NotesViewModel::class.java]

        setUpRecyclerView()

        binding.addNoteBtn.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(ListOfNotesDirections.actionListOfNotesToAddNotes())
        }

        backButtonHandle()

    }

    private fun backButtonHandle() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    activity?.let { ActivityCompat.finishAffinity(it) }
                }
            })
    }

    private fun setUpRecyclerView() {
        viewModel.allNotes.observe(viewLifecycleOwner) { it ->
            binding.recylerView.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
            binding.recylerView.layoutManager = LinearLayoutManager(context)
            binding.recylerView.adapter =
                RecyclerViewAdapter(it, { note -> viewModel.deleteNotes(note) },
                    { note -> changeFrag(note) })
        }
    }
    fun changeFrag(note: Notes) {
        findNavController().navigate(
            ListOfNotesDirections.actionListOfNotesToAddNotes()
        )
    }
}