package com.enestigli.todoapp.domain.use_case.update_note

import com.enestigli.todoapp.room.Note
import com.enestigli.todoapp.domain.repository.INoteRepository
import com.enestigli.todoapp.util.Resource
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class UpdateNoteUseCase @Inject constructor(
    private val repository: INoteRepository
) {


     fun update(note: Note) : Resource<Note> {

        try {
            runBlocking {
                repository.update(note)
            }
        }
        catch (e:Exception) {

            return Resource.error("UpdateNoteUseCase Error")

        }

        return Resource.success()


    }
}