package com.martianlab.recipes.domain

import com.martianlab.recipes.entities.Category
import com.martianlab.recipes.entities.Recipe
import com.martianlab.recipes.entities.RecipeTag


interface DbApi{

    suspend fun getRecipes(tag: RecipeTag) : List<Recipe>

    suspend fun getRecipeById(id : Long ) : Recipe

    suspend fun insert(recipe: Recipe)

    suspend fun insert(recipeList : List<Recipe>)

    suspend fun loadCategories() : List<Category>

}
