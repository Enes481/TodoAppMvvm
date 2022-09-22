package com.enestigli.todoapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Note(

    @ColumnInfo(name = "note") val note:String,
    @ColumnInfo(name = "title") val title:String,
    @ColumnInfo(name = "noteDate") val noteDate:String,
    @ColumnInfo(name = "EditedNoteDate") val EditedNoteDate:String?=null,
    @ColumnInfo(name = "editedNote") val editedNote:String?=null,
    @ColumnInfo(name = "priority") val priority:String?=null,
    @PrimaryKey(autoGenerate = true) val uid:Int?=null

)
