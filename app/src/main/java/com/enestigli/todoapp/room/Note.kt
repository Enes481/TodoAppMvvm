package com.enestigli.todoapp.room


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize



@Parcelize
@Entity(tableName = "Notes")
data class Note(

    @ColumnInfo(name = "note") val note:String,
    @ColumnInfo(name = "title") val title:String,
    @ColumnInfo(name = "noteDate") val noteDate:String,
    @ColumnInfo(name = "priority") val priority:String?=null,
    @PrimaryKey(autoGenerate = true) val uid:Int?=null,
    @ColumnInfo(name = "EditedNoteDate") val EditedNoteDate:String?=null,
    //@ColumnInfo(name = "editedNote") val editedNote:String?=null


) : Parcelable
