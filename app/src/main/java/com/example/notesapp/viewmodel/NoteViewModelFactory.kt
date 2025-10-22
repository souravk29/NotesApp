package com.example.notesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.repository.NotesRepository
import com.example.notesapp.roomdb.NotesDB
import android.app.Application


/*

if your viewmodel requires additional parameters such as repository or context, we need to
create a "ViewModelProvider.Factory" to handle the instantiation

 */


class NoteViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(NoteViewModel::class.java)){

            // DO THE HEAVY WORK HERE (on a background thread)
            val database = NotesDB.getInstance(application)
            val repository = NotesRepository(database.notesDao)


            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown View Model Class")

    }
}