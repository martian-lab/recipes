package com.martianlab.drunkennavigation.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "recipe", primaryKeys = arrayOf("id"))
data class Recipe(
    @ColumnInfo(name = "id")
    var id:Int = 0,
    @ColumnInfo(name = "name")
    val name : String,
    @ColumnInfo(name = "complexity")
    val complexity : Int,
    @ColumnInfo(name = "number_persons")
    val numberPerson : Int,
    @ColumnInfo(name = "imageUrl")
    val imageUrl : String,
    @ColumnInfo(name = "description")
    val description : String
)