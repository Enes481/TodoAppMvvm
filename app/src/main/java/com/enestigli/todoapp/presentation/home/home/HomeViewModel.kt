package com.enestigli.todoapp.presentation.home.home


import androidx.lifecycle.ViewModel
import com.enestigli.todoapp.repository.INoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: INoteRepository
): ViewModel() {

    val noteList = repository.getNote()


}