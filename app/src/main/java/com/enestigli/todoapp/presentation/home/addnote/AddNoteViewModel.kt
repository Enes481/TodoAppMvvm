package com.enestigli.todoapp.presentation.home.addnote

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enestigli.todoapp.application.Application
import com.enestigli.todoapp.base.BaseViewModel
import com.enestigli.todoapp.repository.INoteRepository
import com.enestigli.todoapp.room.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val repository: INoteRepository,

): ViewModel() {



    private fun insertNote(note: Note) = viewModelScope.launch{
        repository.Insert(note)
    }


    fun makeNote(title:String,note:String,currentDate:String,priority:String?){
        if(title.isEmpty() || note.isEmpty() ){
            println("Enter titel,note,priority")
            return
        }


        val note = Note(note,title,currentDate,priority)
        insertNote(note)

    }



}