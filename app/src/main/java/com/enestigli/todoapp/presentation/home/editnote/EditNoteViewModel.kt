package com.enestigli.todoapp.presentation.home.editnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.enestigli.todoapp.base.BaseViewModel
import com.enestigli.todoapp.room.Note
import com.enestigli.todoapp.domain.use_case.update_note.UpdateNoteUseCase
import com.enestigli.todoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private val editNoteUseCase:UpdateNoteUseCase
):BaseViewModel() {

    private var insertArtMsg = MutableLiveData<Resource<Note>>()
    val insertArtMessage: LiveData<Resource<Note>>
        get() = insertArtMsg


    fun updateNote(note: Note) = viewModelScope.launch{
        editNoteUseCase.update(note)
    }

    fun resetUpdateNoteMsg(){
        insertArtMsg = MutableLiveData<Resource<Note>>()
    }

    fun makeNote(id:Int?,title:String,note:String,EditDate:String,currentDate:String,priority:String?,category:String){


        if(title.isEmpty() || note.isEmpty() || priority.isNullOrEmpty() || category.isEmpty()){

            insertArtMsg.postValue(Resource.error("Enter Title,Note,Priority,Category.",null))
            return
        }
        else{
            println("category -- > "+category)
            val note = Note(note,title,currentDate,category,priority,id,EditDate)
            updateNote(note)
            insertArtMsg.postValue(Resource.success(note))

        }



    }

}