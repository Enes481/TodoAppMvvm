package com.enestigli.todoapp.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Query("SELECT * FROM Notes")
    fun observeNotes(): LiveData<List<Note>> //suspend yazmadık çünkü livedata zaten asenkron çalışır.

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Insert(note:Note)


    @Delete
    suspend fun Delete(note:Note)

    @Query("DELETE FROM Notes")
    suspend fun deleteAll()

    @Update
    suspend fun update(note:Note)
}