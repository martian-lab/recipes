package com.martianlab.drunkennavigation.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "category", primaryKeys = arrayOf("id"))
data class Category(
    @ColumnInfo(name = "id")
    var id:Int = 0,
    @ColumnInfo(name = "imageUrl")
    val imageUrl : String?,
    @ColumnInfo(name = "name")
    val name : String,
    @ColumnInfo(name = "sort")
    val sort : Int
)