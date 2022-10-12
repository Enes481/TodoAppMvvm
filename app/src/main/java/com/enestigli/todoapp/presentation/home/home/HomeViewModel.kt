package com.enestigli.todoapp.presentation.home.home



import androidx.lifecycle.viewModelScope
import com.enestigli.todoapp.base.BaseViewModel
import com.enestigli.todoapp.room.Note
import com.enestigli.todoapp.domain.use_case.delete_all_note.DeleteAllNoteUseCase
import com.enestigli.todoapp.domain.use_case.delete_note.DeleteNoteUseCase
import com.enestigli.todoapp.domain.use_case.get_note.GetNoteUseCase
import com.enestigli.todoapp.domain.use_case.update_note.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val deleteAllNoteUseCase: DeleteAllNoteUseCase,
    private val editNoteUseCase: UpdateNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val getNoteUseCase: GetNoteUseCase
): BaseViewModel() {

    val noteList = getNoteUseCase.getNote()


    fun deleteNote(note: Note) = viewModelScope.launch{
        deleteNoteUseCase.delete(note)
    }


    fun editNote(note: Note) = viewModelScope.launch {
        editNoteUseCase.update(note)
    }


    fun deleteAll() = viewModelScope.launch{
        deleteAllNoteUseCase.deleteAll()
    }





}