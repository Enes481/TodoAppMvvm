package com.enestigli.todoapp.room

import androidx.room.*

@Dao
interface NoteDao {

    @Query("SELECT * FROM Notes")
    suspend fun getNotes():List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Insert(note:Note)


    @Delete
    suspend fun Delete(note:Note)

    @Query("DELETE FROM Notes")
    suspend fun deleteAll()

    @Update
    suspend fun update(note:Note)
}