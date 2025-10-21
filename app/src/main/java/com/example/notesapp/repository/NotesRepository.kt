package com.example.notesapp.repository

import androidx.lifecycle.LiveData
import com.example.notesapp.roomdb.Note
import com.example.notesapp.roomdb.NoteDao




class NotesRepository (private val noteDao: NoteDao){                               // defining all methods in Dao, to combine all operation

    val allNotes : LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insertNote(note: Note){
        return noteDao.insert(note)
    }

}







/*

Repository : Serves as a single source of truth  for data in our app ,
             handling all data operations :  1. Fetching data from network
                                             2. loading data from a local DB

             repository acts as a mediator between the "ViewModel" and the data sources, providing a clean api for data ops ensuring clear
             separation of concerns , it enhances flexibility and maintainability of code.

val -> read only variable ******************

 */




/*
1. val allNotes
This declares a read-only variable named allNotes. This variable will be the single source of truth for your app's complete list of notes. It's typically declared in a ViewModel or a Repository.

2. : LiveData<List<Note>>
This is the type declaration.
Note: This is your data model class, likely a Room @Entity that defines the structure of a "Note" table in
your database (e.g., data class Note(val id: Int, val title: String, val content: String)).

List<Note>: This means the variable will hold a list of those Note objects.
LiveData<...>: This is the most important part. LiveData is a special class from Android Architecture Components. It's an observable data holder. This means:
It holds data: In this case, it holds a List<Note>.
It's observable: Other components in your app (like your Activity or Fragment) can "observe" it. When the data it holds changes, it automatically notifies all its observers.
It's lifecycle-aware: It only updates observers that are in an active lifecycle state (like STARTED or RESUMED). This prevents app crashes, for example, by not trying to update a UI that has already been destroyed.

3. = noteDao.getAllNotes()
This is the assignment.
noteDao: This is your Data Access Object (DAO). It's an interface you define where you tell Room how to interact with your database. You annotate methods with @Query, @Insert, @Delete, etc.
".getAllNotes()" : This is a method you defined inside your noteDao interface. It probably looks something like this:
When you declare the return type of a Room query as LiveData, Room does something magical: it automatically runs this query on a background thread and wraps the result in a LiveData object.
 */