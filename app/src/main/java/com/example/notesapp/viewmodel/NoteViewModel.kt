package com.example.notesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.repository.NotesRepository
import com.example.notesapp.roomdb.Note
import com.example.notesapp.roomdb.NotesDB
import kotlinx.coroutines.launch


/*

ViewModel : Store and manage ui related data, separating the UI related logic from UI controller(Composable, activity, fragment)


viewmodel are lifecycle aware (eg. data survives screen rotation) --> retains data across configuration changes

 */



class NoteViewModel (private val repository: NotesRepository ): ViewModel() {                                               // inherited "ViewModel"

    val allNotes: LiveData<List<Note>> = repository.allNotes

    fun insert (note: Note) =
        viewModelScope.launch {                                                             // launch is coroutine builder, that launches new coroutine
            repository.insertNote(note)                                                     // without blocking the current thread.
        }

}