package com.example.notesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Notes::class], version = 1)
abstract class NotesDatabase : RoomDatabase(){

    abstract fun getNotesDao() : NotesDao

    companion object{

        @Volatile
        private var INSTANCE: NotesDatabase? = null

        fun getDatabase(context: Context): NotesDatabase {

            if (INSTANCE == null) {
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NotesDatabase::class.java,
                        "notes_database"
                    ).build()
                }
            }
            return INSTANCE!!

        }
    }
}