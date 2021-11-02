package com.example.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fragment.RoomDB.NotesTable
import kotlinx.android.synthetic.main.item_view.view.*

class RecycelerAdapter (val activity: FragmentHome, val NoteList:List<NotesTable>): RecyclerView.Adapter<RecycelerAdapter.itemViewHolder>() {
    class itemViewHolder  (itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        return itemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_view,parent,false
            ))
    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        val noteObject=NoteList[position]
        var id= NoteList[position].id
        var note=NoteList[position].note

        holder.itemView.apply {
            tvNoteNum.text="Note $id: "
            tvNote.text=note

            EditActionButton.setOnClickListener(){
                activity.goFragmentUpdate(id)
            }

            DelActionButton.setOnClickListener(){
                activity.delNote(noteObject)
            }

        }//end holder
    }

    override fun getItemCount(): Int= NoteList.size
}