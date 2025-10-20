package com.example.notesapp.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes_table")
data class Note(                                                        // => "Note" will act as an Entity, "an entity" is a table in room
                                                                        //     and the table name is "notes_table", if we do not specify table name
                                                                        //     then and std will implicitly assume "Note" to be the table name


    @PrimaryKey(autoGenerate = true)                                    // everytime we create a new instance of note, id will be incremented by
    val id: Int = 0,                                                    // one automatically and it'll be unique (primary key is always unique)



    val title: String,
    val description: String,
    val color: Int                                                      // color as ARGB integer, bcoz Room directly doesn't support complex types like "Color"
)




/*

@ColumnInfo(name = "note_title)
val title: String


we can specify column name as this as well but if we don't specify it as we did here, and std will implicitly consider the variable name to be the name of the column (which is alright btw)



 */