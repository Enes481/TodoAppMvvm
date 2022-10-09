package com.enestigli.todoapp.presentation.home.addnote


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enestigli.todoapp.room.Note
import com.enestigli.todoapp.domain.use_case.insert_note.InsertNoteUseCase
import com.enestigli.todoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val addNoteUseCase: InsertNoteUseCase,

): ViewModel() {

    private var insertArtMsg = MutableLiveData<Resource<Note>>()
    val insertArtMessage: LiveData<Resource<Note>>
        get() = insertArtMsg

    fun resetInsertNoteMsg(){
        insertArtMsg = MutableLiveData<Resource<Note>>()
    }

    private fun insertNote(note: Note) = viewModelScope.launch{
        addNoteUseCase.insert(note)
    }


    fun makeNote(title:String,note:String,currentDate:String,priority:String?,category:String){


        if(title.isEmpty() || note.isEmpty() || priority.isNullOrEmpty() || category.isEmpty() ){

            insertArtMsg.postValue(Resource.error("Enter Title,Note,Priority,category.",null))
            return
        }
        else{

            val note = Note(note,title,currentDate,category,priority)
            insertNote(note)
            insertArtMsg.postValue(Resource.success(note))
        }



    }



}