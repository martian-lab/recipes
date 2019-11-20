package com.martianlab.recipes.tools.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_ingredient", foreignKeys = [ForeignKey(entity = RecipeEntity::class, parentColumns = ["id"], childColumns = ["recipe_id"], onDelete = ForeignKey.CASCADE)])
data class RecipeIngredientEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Long,
    @ColumnInfo(name = "recipe_id")
    val recipeId : Long,
    val title : String,
    val quantity : String,
    val measureUnit : String,
    val position : Int
)