package com.enestigli.todoapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Note(

    @ColumnInfo(name = "note") val note:String?,
    @ColumnInfo(name = "editedNote") val editedNote:String?,
    @ColumnInfo(name = "priority") val priority:Int?,
    @ColumnInfo(name = "noteDate") val noteDate:String?,
    @ColumnInfo(name = "EditedNoteDate") val EditedNoteDate:String?=null,
    @PrimaryKey(autoGenerate = true) val uid:Int?=null
)
