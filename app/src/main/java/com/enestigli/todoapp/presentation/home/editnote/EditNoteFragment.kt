package com.enestigli.todoapp.presentation.home.editnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.enestigli.todoapp.R
import com.enestigli.todoapp.databinding.FragmentAddNoteBinding
import com.enestigli.todoapp.databinding.FragmentEditNoteBinding
import com.enestigli.todoapp.room.Note
import com.enestigli.todoapp.util.Priority
import com.enestigli.todoapp.util.Status
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class EditNoteFragment : Fragment(R.layout.fragment_edit_note) {

    private val viewModel:EditNoteViewModel by viewModels()
    private lateinit var fragmentEditNoteBinding: FragmentEditNoteBinding


    private val args by navArgs<EditNoteFragmentArgs>()
    private var Editpriority:String? = null
    private var uid:Int? = null
    private val sdf = SimpleDateFormat("yyyy-MM-dd")
    private val EditDate = sdf.format(Date())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentEditNoteBinding.bind(view)
        fragmentEditNoteBinding = binding

        subscribeToObservers()

        binding.EditNoteFragmentTxtTitle.setText(args.currentNote.title)
        binding.EditNoteFragmentTxtNote.setText(args.currentNote.note)
        binding.EditNoteFragmentTxtCategory.setText(args.currentNote.category)

        uid = args.currentNote.uid




        val currentPriority = args.currentNote.priority

        when(currentPriority){

            "High" ->{
                binding.EditNoteFragmentRadioGroup.check(R.id.EditNoteFragmentHighPriorityRadioBtn)
            }

            "Medium" -> {
                binding.EditNoteFragmentRadioGroup.check(R.id.EditNoteFragmentMediumPriorityRadioBtn)
            }

            "Low" -> {
                binding.EditNoteFragmentRadioGroup.check(R.id.EditNoteFragmentLowPriorityRadioBtn)
            }
        }

        binding.EditNoteFragmentBtn.setOnClickListener{

            checkedRadioButton()

            viewModel.makeNote(
                uid,
                binding.EditNoteFragmentTxtTitle.text.toString(),
                binding.EditNoteFragmentTxtNote.text.toString(),
                EditDate,
                args.currentNote.noteDate,
                Editpriority,
                binding.EditNoteFragmentTxtCategory.text.toString()
            )

            //findNavController().navigate(AddNoteFragmentDirections.actionAddNoteFragmentToHomeFragment())



        }



    }




    private fun subscribeToObservers(){


        viewModel.insertArtMessage.observe(viewLifecycleOwner, androidx.lifecycle.Observer {

            when(it.status){
                Status.SUCCESS ->{
                    Toast.makeText(requireContext(),"Success", Toast.LENGTH_LONG).show()
                    findNavController().popBackStack()
                    viewModel.resetUpdateNoteMsg() //note message success den errora loading e çevirmemek için yaptık ,boş bıraktık

                }

                Status.ERROR ->{
                    Toast.makeText(requireContext(),it.message ?: "Error", Toast.LENGTH_LONG).show()
                }

                Status.LOADING ->{

                }
            }
        })

    }

    private fun checkedRadioButton(){

        val rbId = fragmentEditNoteBinding.EditNoteFragmentRadioGroup.getCheckedRadioButtonId()

        when (rbId) {

            R.id.EditNoteFragmentHighPriorityRadioBtn-> Editpriority = Priority.HIGH.priority
            R.id.EditNoteFragmentMediumPriorityRadioBtn-> Editpriority = Priority.MEDIUM.priority
            R.id.EditNoteFragmentLowPriorityRadioBtn-> Editpriority = Priority.LOW.priority
        }
    }





}