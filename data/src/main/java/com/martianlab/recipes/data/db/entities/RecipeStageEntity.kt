package com.martianlab.recipes.tools.db.entities

import androidx.room.*

@Entity(
    tableName = "recipe_entity",
    primaryKeys = ["id"],
    indices = [Index("recipe_id")],
    foreignKeys = [ForeignKey(entity = RecipeEntity::class, parentColumns = ["id"], childColumns = ["recipe_id"], onDelete = ForeignKey.CASCADE)]
)
data class RecipeStageEntity(
    //@PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Long,
    @ColumnInfo(name = "recipe_id")
    val recipeId : Long,
    val imageURL : String,
    val text : String,
    val step : Int
)