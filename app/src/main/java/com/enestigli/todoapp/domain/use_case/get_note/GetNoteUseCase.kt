package com.enestigli.todoapp.domain.use_case.get_note

import androidx.lifecycle.LiveData
import com.enestigli.todoapp.room.Note
import com.enestigli.todoapp.domain.repository.INoteRepository
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(
    private val repository: INoteRepository
) {


    fun getNote() :LiveData<List<Note>> {


        return repository.getNote()


    }
}