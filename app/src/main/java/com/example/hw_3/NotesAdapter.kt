package com.example.hw_3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale

class NotesAdapter(
    private val onNoteClick: (Long) -> Unit
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val items = mutableListOf<Note>()

    fun submitNotes(notes: List<Note>) {
        val diffCallback = NotesDiffCallback(items, notes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items.clear()
        items.addAll(notes)
        diffResult.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view, onNoteClick)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class NoteViewHolder(
        itemView: View,
        private val onNoteClick: (Long) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvText: TextView = itemView.findViewById(R.id.tvText)

        private val dateFormat = SimpleDateFormat("dd MMM. yyyy", Locale.getDefault())

        fun bind(note: Note) {
            tvDate.text = dateFormat.format(note.createdAt)

            tvTitle.text = note.title.ifBlank { "Без заголовка" }

            val preview = note.text.replace("\n", " ")
            tvText.text = if (preview.length > 80) {
                preview.take(77) + "…"
            } else {
                preview
            }

            itemView.setOnClickListener {
                onNoteClick(note.id)
            }
        }
    }
}
