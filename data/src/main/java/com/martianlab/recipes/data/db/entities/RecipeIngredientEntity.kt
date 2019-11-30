package com.martianlab.recipes.tools.db.entities

import androidx.room.*

@Entity(
    tableName = "recipe_ingredient",
    primaryKeys = ["id", "recipe_id"],
    indices = [Index("recipe_id"), Index("title")],
    foreignKeys = [ForeignKey(entity = RecipeEntity::class, parentColumns = ["id"], childColumns = ["recipe_id"], onDelete = ForeignKey.CASCADE)]
)
data class RecipeIngredientEntity(
    //@PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Long,
    @ColumnInfo(name = "recipe_id")
    val recipeId : Long,
    val title : String,
    val quantity : String,
    val measureUnit : String,
    val position : Int
)