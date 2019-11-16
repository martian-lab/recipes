package com.martianlab.recipes.tools.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Long = 0,
    @ColumnInfo(name = "imageUrl")
    val imageUrl : String?,
    @ColumnInfo(name = "title")
    val title : String,
    @ColumnInfo(name = "sort")
    val sort : Int = Int.MAX_VALUE,
    @ColumnInfo(name = "total")
    val total:Int = 0
)