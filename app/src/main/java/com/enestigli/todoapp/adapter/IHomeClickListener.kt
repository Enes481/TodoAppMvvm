package com.enestigli.todoapp.adapter

import com.enestigli.todoapp.room.Note

interface IHomeClickListener {
    fun deleteNote(note:Note)
    fun editNote(note: Note)
}