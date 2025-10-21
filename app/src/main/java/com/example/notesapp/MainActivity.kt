package com.example.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.ListItemDefaults.containerColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.repository.NotesRepository
import com.example.notesapp.roomdb.Note
import com.example.notesapp.roomdb.NotesDB
import com.example.notesapp.screens.DisplayDialog
import com.example.notesapp.screens.DisplayNotesList
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.viewmodel.NoteViewModel
import com.example.notesapp.viewmodel.NoteViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Room db
        val database = NotesDB.getInstance(applicationContext)

        // Repository
        val repository = NotesRepository(database.notesDao)

        // ViewModel Factory
        val viewModelFactory = NoteViewModelFactory(repository)

        // View Model
        val noteViewModel = ViewModelProvider(
            owner = this,
            viewModelFactory,
        )[NoteViewModel::class.java]



        setContent {
            NotesAppTheme {

                Scaffold(
                    floatingActionButton =
                ) {  }



                // Display all records in room DB
                val notes by noteViewModel
                    .allNotes.observeAsState(emptyList())                          // "observeAsState()" : Converts a live data into a state
                                                                                           //                     object that can be observed within a composable function

                DisplayNotesList(notes = notes)

            }

        }
    }
}


@Composable
fun MyFAB(viewModel: NoteViewModel) {
    FloatingActionButton(
        onclick = {
            DisplayDialog(viewModel = )
        },
        containerColor = Color.Blue,
        contentColor = Color.White,
    ) {

    }
}