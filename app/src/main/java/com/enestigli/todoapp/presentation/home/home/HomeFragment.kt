package com.enestigli.todoapp.presentation.home.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.view.menu.MenuBuilder
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.enestigli.todoapp.R
import com.enestigli.todoapp.adapter.HomeRecyclerAdapter
import com.enestigli.todoapp.adapter.IHomeClickListener
import com.enestigli.todoapp.databinding.FragmentHomeBinding
import com.enestigli.todoapp.room.Note
import com.enestigli.todoapp.util.AlertDialogMessages
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home),IHomeClickListener {


    private val homeRecyclerAdapter = HomeRecyclerAdapter(this)

    private val viewModel: HomeViewModel by viewModels()
    private var fragmentBinding : FragmentHomeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        //viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        val menuHost: MenuHost = requireActivity()


        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here

                menuInflater.inflate(R.menu.menu, menu)

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.deleteAllItem -> {

                        viewModel.noteList.observe(viewLifecycleOwner,Observer {

                            if(it.isNotEmpty()){
                                showAlertDialog()
                            }

                        })


                        true
                    }

                    else -> false
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)



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

    override fun deleteNote(note: Note) {
        viewModel.deleteNote(note)
    }

    override fun editNote(note: Note) {
        //viewModel.editNote(note)

        //println("note id ---> "+note.uid)
        val action:NavDirections = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(note)
        findNavController().navigate(action)

    }



    fun showAlertDialog() {

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Warning !")
            .setIcon(R.drawable.ic_warning3)
            .setMessage(AlertDialogMessages.DeleteAllNotesMsg.msg)
            .setNegativeButton("no"){_,_ ->

                showSnackBar(AlertDialogMessages.DeclineMsg.msg)

            }
            .setPositiveButton("Yes"){dialog,_  ->

                showSnackBar(AlertDialogMessages.DeletedNotesMsg.msg)
                viewModel.deleteAll()
                dialog.cancel()
            }

            .show()



    }


    private fun showSnackBar(msg:String){

        Snackbar.make(requireView(),msg,Snackbar.LENGTH_LONG).show()
    }



}