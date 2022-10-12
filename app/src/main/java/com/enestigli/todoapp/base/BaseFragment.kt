package com.enestigli.todoapp.base

import androidx.fragment.app.Fragment

abstract class BaseFragment<VM:BaseViewModel> : Fragment(){

    abstract val viewModel:VM
}