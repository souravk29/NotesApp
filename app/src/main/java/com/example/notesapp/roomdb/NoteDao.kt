package com.example.notesapp.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


/*

 Data access object (Dao) :  hides all the complexities involved in performing the database operations in the
                                  underlying persistence layer, separate from the rest of the application.
                       (its typically an interface or an abstract class where we define methods for various db operations)




 "Suspend" fun : used in coroutines in kotlin to perform asynchronous operations, without blocking the main thread

 LiveData : An observable data holder class from the Android Jetpack libraries.
            Think of it as a "smart" container for a piece of data (like a user's name, a list of posts, or a score).
            Your UI (like an Activity or Fragment) can "observe" this container. When the data inside it changes,
            LiveData automatically notifies your UI so it can update itself. Its most important feature is that
            it is lifecycle-aware.

 */


@Dao
interface NoteDao {

    // define methods for DB ops         eg: insert, delete and update

    @Insert
    suspend fun insert(note: Note)

    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): LiveData<List<Note>>




}