package com.enestigli.todoapp.presentation.home.home


import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enestigli.todoapp.repository.INoteRepository
import com.enestigli.todoapp.room.Note
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: INoteRepository
): ViewModel() {

    val noteList = repository.getNote()


    fun deleteNote(note: Note) = viewModelScope.launch{
        repository.Delete(note)
    }


    fun editNote(note:Note) = viewModelScope.launch {
        repository.update(note)
    }


    fun deleteAll() = viewModelScope.launch{
        repository.deleteAll()
    }





}