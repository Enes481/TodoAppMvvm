package com.enestigli.todoapp.domain.use_case.delete_note

import com.enestigli.todoapp.room.Note
import com.enestigli.todoapp.domain.repository.INoteRepository
import com.enestigli.todoapp.util.Resource
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val repository: INoteRepository
) {


    fun delete(note: Note) : Resource<Note>{

        try {
            runBlocking {
                repository.Delete(note)
            }
        }
        catch (e:Exception) {

            return Resource.error("DeleteNoteUseCaseError")

        }

        return Resource.success()


    }
}