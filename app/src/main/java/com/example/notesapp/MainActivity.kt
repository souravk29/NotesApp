package com.example.notesapp

import android.R.attr.contentDescription
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItemDefaults.containerColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.roomdb.Note
import com.example.notesapp.screens.DisplayDialog
import com.example.notesapp.screens.DisplayNotesList
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.viewmodel.NoteViewModel
import com.example.notesapp.viewmodel.NoteViewModelFactory


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // ViewModel Factory
        val viewModelFactory = NoteViewModelFactory(application)

        // View Model
        val noteViewModel = ViewModelProvider(
            owner = this,
            viewModelFactory,
        )[NoteViewModel::class.java]



        setContent {
            NotesAppTheme {

               Scaffold (
                   floatingActionButton = { MyFAB(viewModel = noteViewModel)}
               ){
                   val notes by noteViewModel
                       .allNotes.observeAsState(emptyList())

                   DisplayNotesList(notes = notes)
               }


            }

        }
    }
}


@Composable
fun MyFAB(viewModel: NoteViewModel) {

    // controlling the dialog appearance
    var showDialog by remember {
        mutableStateOf(false)
    }

    DisplayDialog(
        viewModel = viewModel,
        showDialog = showDialog
    ) {
        showDialog = false
    }

    FloatingActionButton(
        onClick = { showDialog = true },
        containerColor = Color.Blue,
        contentColor = Color.White,
    ) {

        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add"
        )

    }
}


                    /*       "observeAsState()" : Converts a live data into a state object that can be observed within a composable function   */

                    // Display all records in room DB


