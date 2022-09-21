package com.enestigli.todoapp.base

import androidx.lifecycle.AndroidViewModel
import com.enestigli.todoapp.application.Application
import com.enestigli.todoapp.repository.NoteRepository

abstract class BaseViewModel(app: Application) : AndroidViewModel(app) {

}