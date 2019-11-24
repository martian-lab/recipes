package com.martianlab.recipes.tools.db.entities

import androidx.room.*


@Entity(
    tableName = "recipe_tag",
    primaryKeys = ["id", "recipe_id"],
    indices = [Index("recipe_id")],
    foreignKeys = [ForeignKey(entity = RecipeEntity::class, parentColumns = ["id"], childColumns = ["recipe_id"], onDelete = ForeignKey.CASCADE)])
data class RecipeTagEntity(
    @ColumnInfo(name = "id")
    val id : Long = 0,
    @ColumnInfo(name = "title")
    val title : String,
    @ColumnInfo(name = "recipe_id")
    val recipeId : Long
)