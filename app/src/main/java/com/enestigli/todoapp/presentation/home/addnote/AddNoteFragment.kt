package com.enestigli.todoapp.presentation.home.addnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.enestigli.todoapp.R
import com.enestigli.todoapp.databinding.FragmentAddNoteBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class AddNoteFragment @Inject constructor(): Fragment(R.layout.fragment_add_note) {

    private var fragmentAddNoteBinding:FragmentAddNoteBinding? = null
    private lateinit var viewModel:AddNoteViewModel

    private val sdf = SimpleDateFormat("yyyy-MM-dd")
    private val currentDate = sdf.format(Date())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(AddNoteViewModel::class.java)

        val binding =FragmentAddNoteBinding.bind(view)
        fragmentAddNoteBinding = binding


        binding.AddNoteFragmentBtn.setOnClickListener{


            viewModel.makeNote(
                binding.AddNoteFragmentTxtTitle.text.toString(),
                binding.AddNoteFragmentTxtNote.text.toString(),
                currentDate


            )
        }


    }





}