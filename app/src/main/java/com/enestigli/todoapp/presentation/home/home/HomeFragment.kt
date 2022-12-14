package com.enestigli.todoapp.presentation.home.home

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.enestigli.todoapp.R
import com.enestigli.todoapp.base.BaseBindingFragment
import com.enestigli.todoapp.presentation.home.adapter.HomeRecyclerAdapter
import com.enestigli.todoapp.presentation.home.adapter.IHomeClickListener
import com.enestigli.todoapp.databinding.FragmentHomeBinding
import com.enestigli.todoapp.room.Note
import com.enestigli.todoapp.util.AlertDialogMessages
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseBindingFragment<FragmentHomeBinding,HomeViewModel>(),IHomeClickListener {


    private val homeRecyclerAdapter = HomeRecyclerAdapter(this)


    //private var fragmentBinding : FragmentHomeBinding? = null

    override val viewModel: HomeViewModel by viewModels()
    override val layoutId: Int = R.layout.fragment_home


    override fun onReady(savedInstanceState: Bundle?) {
        super.onReady(savedInstanceState)

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



        //val binding = FragmentHomeBinding.bind(view)
        //fragmentBinding = binding

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



    override fun deleteNote(note: Note) {
        viewModel.deleteNote(note)
    }

    override fun editNote(note: Note) {
        //viewModel.editNote(note)

        //println("note id ---> "+note.uid)
        val action:NavDirections = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(note)
        findNavController().navigate(action)

    }

    override fun setClock() {
        println("setclock Home fragment")
        val action:NavDirections = HomeFragmentDirections.actionHomeFragmentToClockFragment()
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