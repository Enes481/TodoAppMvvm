package com.enestigli.todoapp.presentation.home.addnote

import android.widget.Toast
import androidx.core.graphics.toColorInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enestigli.todoapp.application.Application
import com.enestigli.todoapp.base.BaseViewModel
import com.enestigli.todoapp.repository.INoteRepository
import com.enestigli.todoapp.room.Note
import com.enestigli.todoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val repository: INoteRepository,

): ViewModel() {

    private var insertArtMsg = MutableLiveData<Resource<Note>>()
    val insertArtMessage: LiveData<Resource<Note>>
        get() = insertArtMsg

    fun resetInsertNoteMsg(){
        insertArtMsg = MutableLiveData<Resource<Note>>()
    }

    private fun insertNote(note: Note) = viewModelScope.launch{
        repository.Insert(note)
    }


    fun makeNote(title:String,note:String,currentDate:String,priority:String?){


        if(title.isEmpty() || note.isEmpty() || priority.isNullOrEmpty() ){

            insertArtMsg.postValue(Resource.error("Enter Title,Note,Priority.",null))
            return
        }
        else{

            val note = Note(note,title,currentDate,priority)
            insertNote(note)
            insertArtMsg.postValue(Resource.success(note))
        }



    }



}