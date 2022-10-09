package com.enestigli.todoapp.domain.repository

import androidx.lifecycle.LiveData
import com.enestigli.todoapp.room.Note

interface INoteRepository {


    fun getNote():LiveData<List<Note>>

    suspend fun Insert(note: Note)

    suspend fun Delete(note: Note)

    suspend fun deleteAll()

    suspend fun update(note: Note)
}