package com.example.roomdatabase

import androidx.room.TypeConverter
import java.util.*

class Converters {

    // Type Converters - Basically our SQLite Database supports only few data types
    //now in this case suppose we want to store data in our database we have to convert it in long data type and then we can store
    //In these cases type converters helps
    @TypeConverter
    fun dateToLong(date : Date) : Long{
        return date.time
    }

    @TypeConverter
    fun longToDate(value : Long) : Date{
        return Date(value)
    }
}