package com.example.fragment.RoomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class NotesTable(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    val id : Int = 0,

    @ColumnInfo(name = "Note")
    val note: String
)
