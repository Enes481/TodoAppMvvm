package com.enestigli.todoapp.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseBindingActivity<BINDING: ViewDataBinding,VM:BaseViewModel>: BaseActivity<VM>() {

    @get:LayoutRes
    abstract val layoutId: Int

    protected val binding: BINDING by lazy {
        DataBindingUtil.setContentView(this, layoutId)
    }

    open fun onReady(savedInstanceState: Bundle?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        onReady(savedInstanceState)
    }
}