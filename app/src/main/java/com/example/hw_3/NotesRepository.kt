package com.example.hw_3

object NotesRepository {

    private val notes = mutableListOf<Note>()

    fun getAll(): List<Note> = notes.toList()

    fun getById(id: Long): Note? = notes.find { it.id == id }

    fun createEmpty(): Note {
        val now = System.currentTimeMillis()
        val note = Note(
            id = now,
            title = "",
            text = "",
            createdAt = now
        )
        notes += note
        return note
    }

    fun updateTitle(id: Long, newTitle: String) {
        getById(id)?.title = newTitle
    }

    fun updateText(id: Long, newText: String) {
        getById(id)?.text = newText
    }

    fun deleteIfEmpty(id: Long) {
        val note = getById(id) ?: return
        if (note.title.isBlank() && note.text.isBlank()) {
            notes.remove(note)
        }
    }

    // TODO: заменить in-memory хранение на нормальную (сейчас если перезапуск приложение -> стирание данных)
}
