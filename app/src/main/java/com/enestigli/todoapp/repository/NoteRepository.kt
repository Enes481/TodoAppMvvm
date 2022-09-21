package com.enestigli.todoapp.repository

import com.enestigli.todoapp.application.Application
import com.enestigli.todoapp.room.Note
import com.enestigli.todoapp.room.NoteDao
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao

) : INoteRepository{

    override suspend fun getNotes(): List<Note> {
       return noteDao.getNotes()
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
        noteDao.update(note)
    }
}