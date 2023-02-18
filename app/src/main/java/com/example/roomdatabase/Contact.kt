package com.example.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


//Entity - Basically our DataBase Table
@Entity(tableName = "contact")
data class Contact (
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val name : String ,
    val phone : String,
    val createdDate : Date,
    val isActive : Int //because we cannot store boolean inside the sqlite 0 - inActive , 1 - Active
    )
