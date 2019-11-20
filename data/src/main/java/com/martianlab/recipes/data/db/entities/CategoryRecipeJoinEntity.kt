package com.martianlab.recipes.tools.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "category_recipe_join",
    primaryKeys = ["recipe_id", "category_id"],
    foreignKeys = [
        ForeignKey(entity = RecipeEntity::class, parentColumns = ["id"], childColumns = ["recipe_id"]),
        ForeignKey(entity = CategoryEntity::class, parentColumns = ["id"], childColumns = ["category_id"])
    ])
data class CategoryRecipeJoinEntity (
    @ColumnInfo(name = "recipe_id")
    val recipeId : Long,
    @ColumnInfo(name = "category_id")
    val categoryId : Long
)