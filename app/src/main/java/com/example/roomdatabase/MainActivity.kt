package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var contactDatabase: ContactDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contactDatabase = ContactDatabase.getDataBase(this)

        //without singleton pattern
//        contactDatabase = Room.databaseBuilder(applicationContext,
//            ContactDatabase::class.java,
//            "contactDB").build()

        GlobalScope.launch {
            contactDatabase.contactDao().insertContact(Contact(0,"Deep", "9924441050", Date(),1))
        }

    }

    fun update(view: View) {
        contactDatabase.contactDao().getContact().observe(this,{
            Log.d("tag" , it.toString())
        })
    }
}