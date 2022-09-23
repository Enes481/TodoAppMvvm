package com.enestigli.todoapp.presentation.home.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.enestigli.todoapp.adapter.HomeRecyclerAdapter
import com.enestigli.todoapp.presentation.home.home.HomeFragment
import javax.inject.Inject

class NoteFragmentFactory @Inject constructor(
    private val homeRecyclerAdapter: HomeRecyclerAdapter
) :FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when(className){
            HomeFragment::class.java.name -> HomeFragment(homeRecyclerAdapter)
            else ->super.instantiate(classLoader, className)
        }




    }

}