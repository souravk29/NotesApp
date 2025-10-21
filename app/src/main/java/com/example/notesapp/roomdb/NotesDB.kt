package com.example.notesapp.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NotesDB : RoomDatabase() {

    abstract val notesDao: NoteDao

    companion object {

        @Volatile
        private var INSTANCE: NotesDB? = null                                                                   // initially set to null

        fun getInstance(context: Context): NotesDB {
            synchronized(this) {                                                                         // synchronized assures that only one thread can execute this
                var instance = INSTANCE
                if (instance == null) {

                    instance = Room.databaseBuilder(                                                            // takes 3 parameters

                        context.applicationContext,                                                             // context is needed to access the file system
                        NotesDB::class.java,                                                            // class that represents room db
                        "notes_db"                                                                      // room will use this name for the database

                    ).build()                                                                                   // provided by room to build room database

                }

                INSTANCE = instance
                return instance

            }
        }

    }
}


/*

  Key Concepts & Explanations:

  Room.databaseBuilder() :
    - Static method provided by Room to create a database instance.
    - It uses the builder pattern to configure and construct the db instance.

  Singleton Design Pattern :
    - Ensures only one instance of the database exists.
    - This avoids unnecessary overhead associated with repeated db creation.

  companion object :
    - Used to define the static singleton instance of the db class.

  @Volatile :
    - Prevents any possible race condition in multithreading. Guarantees
      that the variable's value is always read from/written to main memory.

*/

/*

  Code Deep Dive:
  -------------abstract val notesDao: NoteDao---------------

  val:
    - This declares the property as read-only (a "value").
    - Once Room creates it, its reference won't change. You can get the DAO,
      but you can't set it to a different one.

  notesDao:
    - This is the name of the property.
    - It's how you will access your DAO from your database instance
      (e.g., myDatabase.notesDao).

  : NoteDao:
    - This is the type of the property.
    - It specifies that this property will hold an instance of your NoteDao
      interface, which is where you define all your SQL queries with
      annotations like @Query, @Insert, @Update, etc.

*/