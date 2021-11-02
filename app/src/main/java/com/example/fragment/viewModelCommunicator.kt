package com.example.fragment


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class viewModelCommunicator:ViewModel(){

    private val id = MutableLiveData<Any>()
    val getId: LiveData<Any> get() = id

    fun setId(noteId: Int){
        id.value=noteId
    }

}


