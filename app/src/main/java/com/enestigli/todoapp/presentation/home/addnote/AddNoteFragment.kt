package com.enestigli.todoapp.presentation.home.addnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.enestigli.todoapp.R
import com.enestigli.todoapp.databinding.FragmentAddNoteBinding
import com.enestigli.todoapp.util.Priority
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class AddNoteFragment @Inject constructor(): Fragment(R.layout.fragment_add_note) {

    private lateinit var fragmentAddNoteBinding:FragmentAddNoteBinding
    private lateinit var viewModel:AddNoteViewModel

    private val sdf = SimpleDateFormat("yyyy-MM-dd")
    private val currentDate = sdf.format(Date())

    private var priority:String? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(AddNoteViewModel::class.java)

        val binding = FragmentAddNoteBinding.bind(view)
        fragmentAddNoteBinding = binding



        binding.AddNoteFragmentBtn.setOnClickListener{

            checkedRadioButton()

            viewModel.makeNote(
                binding.AddNoteFragmentTxtTitle.text.toString(),
                binding.AddNoteFragmentTxtNote.text.toString(),
                currentDate,
                priority
            )

            findNavController().navigate(AddNoteFragmentDirections.actionAddNoteFragmentToHomeFragment())



        }


    }


    private fun checkedRadioButton(){

        val rbId = fragmentAddNoteBinding.AddNoteFragmentRadioGroup.getCheckedRadioButtonId()

        when (rbId) {

            R.id.AddNoteFragmentHighPriorityRadioBtn-> priority = Priority.HIGH.priority
            R.id.AddNoteFragmentMediumPriorityRadioBtn-> priority = Priority.MEDIUM.priority
            R.id.AddNoteFragmentLowPriorityRadioBtn-> priority = Priority.LOW.priority
        }
    }

}
