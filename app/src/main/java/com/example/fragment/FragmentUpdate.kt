package com.example.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.fragment.RoomDB.NotesDataBase
import com.example.fragment.RoomDB.NotesTable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FragmentUpdate : Fragment() {
    private val modelCommunicator: viewModelCommunicator by activityViewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_update, container, false)
        var idN:Int?=null
        val edNotes = view.findViewById<EditText>(R.id.edNots)
        val btnSumbmit = view.findViewById<Button>(R.id.btnSubmit)

        btnSumbmit.setOnClickListener() {
            modelCommunicator.getId.observe(viewLifecycleOwner,  Observer { t->
                idN=t.toString().toInt()
            })
            if (edNotes.text.isNotEmpty()) {
                CoroutineScope(Dispatchers.IO).launch {
                    this.let {
                        val NoteDB = NotesDataBase.getInstance(requireContext())
                        NoteDB.getNotesDao().updateNote(NotesTable(idN.toString().toInt(), edNotes.text.toString()))
                    }
                    }//end CoroutineScope
                Navigation.findNavController(view).navigate(R.id.fragmentHome)
                }//end if
            else
                Toast.makeText(requireContext(), "please fill the missing entry!", Toast.LENGTH_SHORT).show()
            }//lis
        return view
    }//

}//end class