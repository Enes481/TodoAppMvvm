package com.enestigli.todoapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.enestigli.todoapp.R
import com.enestigli.todoapp.room.Note


class HomeRecyclerAdapter (private val clickListener: IHomeClickListener) : RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>(){


    private val diffUtil = object : DiffUtil.ItemCallback<Note>(){ //iki tane Art arasındaki farkı bulucaz

        //itemler aynı mı , eğer oldItem yani eski veri ile newItem yani yeni veri aynıysa true dönücek ,eşit değilse false dönücek
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

        //Biz DiffUtil'u kullanarak bir değişim olduğunda bir güncelleme olduğunda veriler değişmişmi değişmemişmi kontrol edebilecez.
    }

    //DiffUtil helper
    private val recyclerListDiffer = AsyncListDiffer(this,diffUtil)


    var noteList :List<Note>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)



    class HomeViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_row,parent,false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {


        val title = holder.itemView.findViewById<TextView>(R.id.title)
        val desc =  holder.itemView.findViewById<TextView>(R.id.note)
        val priority = holder.itemView.findViewById<TextView>(R.id.priority)
        val dateTime = holder.itemView.findViewById<TextView>(R.id.dateTime)
        val editBtn = holder.itemView.findViewById<ImageView>(R.id.HomeFragmentEditBtn)
        val deleteBtn = holder.itemView.findViewById<ImageView>(R.id.HomeFragmentDeleteBtn)
        val editedDate = holder.itemView.findViewById<TextView>(R.id.editedDate)
        val category = holder.itemView.findViewById<TextView>(R.id.category)
        val notificationClock = holder.itemView.findViewById<ImageView>(R.id.notificationClock)
        val note = noteList[position]

        holder.itemView.apply {
            title?.text = "${note.title}"
            desc?.text = "${note.note}"
            priority?.text = "${note.priority}"
            dateTime?.text = "${note.noteDate}"
            editedDate.text = "${note.EditedNoteDate ?: ""}"
            category.text = "${note.category}"
        }

        deleteBtn.setOnClickListener{
            clickListener.deleteNote(note)
        }

        editBtn.setOnClickListener{

            clickListener.editNote(note)
        }

        notificationClock.setOnClickListener{

            clickListener.setClock()
        }


    }

    override fun getItemCount(): Int {
        return noteList.size
    }
}


