package com.martianlab.recipes.tools.db.entities

import androidx.room.Embedded
import androidx.room.Relation

data class RecipeWithDependencies(

    @Embedded
    val recipeEntity: RecipeEntity,

    @Relation(parentColumn = "id", entityColumn = "recipe_id")
    val tags : Set<RecipeTagEntity>,

    @Relation(parentColumn = "id", entityColumn = "recipe_id")
    val stages : List<RecipeStageEntity>,

    @Relation(parentColumn = "id", entityColumn = "recipe_id")
    val ingredients : List<RecipeIngredientEntity>,

    @Relation(parentColumn = "id", entityColumn = "recipe_id")
    val comments: List<RecipeCommentEntity>


)