package com.example.hw_3

import androidx.recyclerview.widget.DiffUtil

class NotesDiffCallback(
    private val oldList: List<Note>,
    private val newList: List<Note>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    // Один и тот же элемент — если совпадают id
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    // Содержимое элемента не изменилось — сравниваем поля
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.title == newItem.title &&
                oldItem.text == newItem.text &&
                oldItem.createdAt == newItem.createdAt
    }
}
