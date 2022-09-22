package com.enestigli.todoapp.repository

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.enestigli.todoapp.room.Note

interface INoteRepository {


    fun getNote():LiveData<List<Note>>

    suspend fun Insert(note: Note)

    suspend fun Delete(note: Note)

    suspend fun deleteAll()

    suspend fun update(note: Note)
}