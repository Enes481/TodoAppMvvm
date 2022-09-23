package com.enestigli.todoapp.presentation.home.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.enestigli.todoapp.R
import com.enestigli.todoapp.adapter.HomeRecyclerAdapter
import com.enestigli.todoapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment @Inject constructor(
    private val homeRecyclerAdapter:HomeRecyclerAdapter
): Fragment(R.layout.fragment_home) {

    private lateinit var viewModel: HomeViewModel
    private var fragmentBinding : FragmentHomeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        val binding = FragmentHomeBinding.bind(view)
        fragmentBinding = binding

        subscribeToObservers()

        binding.HomeFragmentRecyclerView.adapter = homeRecyclerAdapter
        binding.HomeFragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext())


        binding.fab.setOnClickListener{
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddNoteFragment())
        }



    }

    private fun subscribeToObservers(){
        viewModel.noteList.observe(viewLifecycleOwner, Observer {
            homeRecyclerAdapter.noteList = it
        })
    }


   override fun onDestroy() {
        fragmentBinding = null
        super.onDestroy()

    }


}