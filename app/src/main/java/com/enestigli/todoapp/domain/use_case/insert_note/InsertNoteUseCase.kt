package com.enestigli.todoapp.domain.use_case.insert_note

import com.enestigli.todoapp.room.Note
import com.enestigli.todoapp.domain.repository.INoteRepository
import com.enestigli.todoapp.util.Resource
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class InsertNoteUseCase @Inject constructor(
    private val repository: INoteRepository
) {


     fun insert(note: Note) : Resource<Note> {

        try {
            runBlocking {
                repository.Insert(note)
            }
        }
        catch (e:Exception) {

            return Resource.error("InsertNoteUseCase Error")

        }

        return Resource.success()


    }
}