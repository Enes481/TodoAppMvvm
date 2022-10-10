package com.enestigli.todoapp.data.repository

import androidx.lifecycle.LiveData
import com.enestigli.todoapp.room.Note
import com.enestigli.todoapp.room.NoteDao
import com.enestigli.todoapp.domain.repository.INoteRepository
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao

) : INoteRepository {


    override fun getNote(): LiveData<List<Note>> {
       return noteDao.observeNotes()
    }

    override suspend fun Insert(note: Note) {
        noteDao.Insert(note)
    }

    override suspend fun Delete(note: Note) {
        noteDao.Delete(note)
    }

    override suspend fun deleteAll() {
        noteDao.deleteAll()
    }

    override suspend fun update(note: Note) {

        noteDao.update(note.uid,note.title,note.note,note.priority,note.EditedNoteDate!!,note.category)
    }
}