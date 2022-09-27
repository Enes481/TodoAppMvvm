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

    @Query("UPDATE Notes SET Title =:title, Note =:note,Priority =:priority,EditedNoteDate =:editedDateTime , Category =:category  WHERE uid = :noteId")
    suspend fun update(noteId:Int?,title:String,note:String,priority:String?,editedDateTime:String,category:String)
}