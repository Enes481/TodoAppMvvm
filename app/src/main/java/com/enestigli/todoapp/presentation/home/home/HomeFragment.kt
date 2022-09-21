package com.enestigli.todoapp.presentation.home.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.enestigli.todoapp.R
import com.enestigli.todoapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment @Inject constructor(): Fragment(R.layout.fragment_home) {

    lateinit var viewModel: HomeViewModel
    private var fragmentBinding : FragmentHomeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        val binding = FragmentHomeBinding.bind(view)

        binding.fab.setOnClickListener{
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddNoteFragment())
        }
    }

}