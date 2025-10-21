package com.example.notesapp.roomdb

import androidx.room.Database
import androidx.room.Room



/*

Room.databaseBuilder() : Static method provided by room library to create a room database instance it uses builder pattern to
                          configure and construct the db instance

 */

/*

Singleton Design Pattern :

 */


@Database(entities = [Note::class], version = 1)                                                    // entity "Note" as a class, only one entity here coz we created only one entity in room previously
abstract class NotesDB {

    var instance = Room.databaseBuilder(                                                            // takes 3 parameters

        context = context.applicationContext,                                                       // context is needed to access the file system and resources of application to create  and manage the db
        NotesDB::class.java,                                                                        // class that represents room db, "NotesDB" should be an
                                                                                                    // abstract class that extends room database. this class serves as main access point to the underlying sqlite database
        name = "notes_db"                                                                           // room will use this name to open the database in application's internal storage

    ).build()                                                                                       // provided by room to build room database with above properties
}




