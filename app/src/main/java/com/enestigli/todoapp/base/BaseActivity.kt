package com.enestigli.todoapp.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<VM:BaseViewModel>:AppCompatActivity() {

    abstract val viewModel:VM
}