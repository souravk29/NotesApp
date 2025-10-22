package com.example.notesapp.screens


import android.app.Dialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.example.notesapp.roomdb.Note
import com.example.notesapp.viewmodel.NoteViewModel



@Composable
fun DisplayDialog(
    viewModel: NoteViewModel,
    showDialog: Boolean,
    onDismiss: () -> Unit
    ){

    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    var selectedColor by remember {
        mutableStateOf(Color.Blue)
    }




    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {                                                                           // confirm button takes an composable, check by ctrl+left click

                Button(
                    onClick = {
                        var note = Note(
                            id = 0,
                            title = title,
                            description = description  ,
                            color = selectedColor.toArgb()                                              // Argb = alpha, red, green, blue
                        )

                        // insert into the db
                        viewModel.insert(note)

                    }
                ) {
                    Text(text = "Save Note")
                }

            },
            title = { Text(text = "Enter Note") },
            text = {
                Column {

                    TextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text(text = "Note Title") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text(text = "Note Description") }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Color components
                    MyColorPicker(
                    selectedColor = selectedColor,
                    onColorSelected = { selectedColor = it }
                    )


                }
            },
            dismissButton = {
                Button(
                    onClick = onDismiss
                ) {
                    Text(text = "Cancel")
                }
            }


        )
    }


}