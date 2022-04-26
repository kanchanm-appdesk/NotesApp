package com.example.notesapp.recylerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.database.Notes
import com.example.notesapp.databinding.FragmentItemViewBinding

class RecyclerViewAdapter (
    private val allNotes: List<Notes>,
    private val onDeleteClick: (Notes) -> Unit,
    private val itemClick: (Notes) -> Unit

        ): RecyclerView.Adapter<RecyclerViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = FragmentItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.apply {
            allNotes[position].apply {
                binding.titleTv.text = this.title
                binding.descriptionTv.text = this.description
                binding.deleteBtn.setOnClickListener {
                    onDeleteClick(this)
                }
                binding.itemContainer.setOnClickListener {
                    itemClick(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

}

