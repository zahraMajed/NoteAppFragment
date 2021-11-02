package com.example.fragment.RoomDB

import androidx.room.*

@Dao
interface NotesDao {
    //method to get all data:
    @Query("SELECT * FROM Notes")
    fun getAll(): List<NotesTable>

    //insert a row into the table
    @Insert
    fun insertNote(note:NotesTable)

    //update note
    @Update
    fun updateNote(note: NotesTable)

    //delete note row
    @Delete
    fun delNote(noteid: NotesTable)

}