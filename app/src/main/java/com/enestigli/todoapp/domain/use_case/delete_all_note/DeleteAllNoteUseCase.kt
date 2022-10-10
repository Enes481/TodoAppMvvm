package com.enestigli.todoapp.domain.use_case.delete_all_note

import com.enestigli.todoapp.room.Note
import com.enestigli.todoapp.domain.repository.INoteRepository
import com.enestigli.todoapp.util.Resource
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DeleteAllNoteUseCase @Inject constructor(
    private val repository: INoteRepository
) {

    fun deleteAll() :Resource<Note>{


        try {
            runBlocking {
                repository.deleteAll()
            }
        }catch (e:Exception){
            return Resource.error("DeleteAllNoteUseCase Error !")
        }

        return Resource.success()

    }

}