package com.martianlab.recipes.tools.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_entity", foreignKeys = [ForeignKey(entity = RecipeEntity::class, parentColumns = ["id"], childColumns = ["recipe_id"], onDelete = ForeignKey.CASCADE)])
data class RecipeStageEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Long,
    @ColumnInfo(name = "recipe_id")
    val recipeId : Long,
    val imageURL : String,
    val text : String,
    val step : Int
)