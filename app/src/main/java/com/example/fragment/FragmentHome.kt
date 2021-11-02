package com.example.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavAction
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragment.RoomDB.NotesDataBase
import com.example.fragment.RoomDB.NotesTable
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FragmentHome : Fragment() {

    private val modelCommunicator: viewModelCommunicator by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
         val view=inflater.inflate(R.layout.fragment_home, container, false)

        val edNotes=view.findViewById<EditText>(R.id.edNots)
        val btnSumbmit=view.findViewById<Button>(R.id.btnSubmit)
        getNotes()
        btnSumbmit.setOnClickListener(){
            val NoteDB=NotesDataBase.getInstance(requireContext())
            if(edNotes.text.isNotEmpty()){
                CoroutineScope(Dispatchers.IO).launch {
                    NoteDB.getNotesDao().insertNote(NotesTable(0, edNotes.text.toString()))
                    getNotes()
                }
                Toast.makeText(requireContext(), "data saved successfully!", Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(requireContext(), "please fill the missing entry!", Toast.LENGTH_SHORT).show()
            edNotes.text.clear()
        }//end btnSum listener

        return view
    }

    fun getNotes(){
        CoroutineScope(Dispatchers.IO).launch{
            val NoteDB=NotesDataBase.getInstance(requireContext())
            withContext(Dispatchers.Main){
                rv_main.adapter=RecycelerAdapter(this@FragmentHome, NoteDB.getNotesDao().getAll())
                rv_main.layoutManager=LinearLayoutManager(requireContext())
            }
        }
    }//end getNote()

    fun goFragmentUpdate(id:Int){
        modelCommunicator.setId(id)
        Navigation.findNavController(requireView()).navigate(R.id.fragmentUpdate)
    }

    fun delNote(notesTable: NotesTable){
        CoroutineScope(Dispatchers.IO).launch {
            val NoteDB=NotesDataBase.getInstance(requireContext())
            NoteDB.getNotesDao().delNote(NotesTable(notesTable.id,notesTable.note))
            getNotes()
        }
    }
}