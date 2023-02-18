package com.example.roomdatabase

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Contact::class], version = 2)
@TypeConverters(Converters::class)
abstract class ContactDatabase : RoomDatabase() {
     abstract fun contactDao() : ContactDao

     companion object{
          //Migration - means the process to update the database of app without losing its data
          //migration object for database migration
          val migration_1_2  = object : Migration(1,2){ //Migration(old version , new version)
               override fun migrate(database: SupportSQLiteDatabase) {
                    database.execSQL("ALTER TABLE contact ADD COLUMN isActive INTEGER NOT NULL DEFAULT(1)") // normal sql query to change database
               }

          }
          @Volatile // notifies every threads whenever value is assigned/updated to INSTANCE
          private var INSTANCE : ContactDatabase? = null


          fun getDataBase(context: Context) : ContactDatabase{
               if(INSTANCE==null){
                    kotlin.synchronized(this){ //if multiple threads trying to call same block of code in that case used to make thread safe
                         if(INSTANCE==null){
                              INSTANCE = Room.databaseBuilder(context.applicationContext,
                                   ContactDatabase::class.java,
                                   "contactDB")
                                   .addMigrations(migration_1_2) // to add migration object
                                   .build()
                         }
                    }
               }
               return INSTANCE!!
          }
     }
}