package com.martianlab.recipes.data.db

import androidx.paging.DataSource
import com.martianlab.recipes.entities.Category
import com.martianlab.recipes.entities.Recipe
import com.martianlab.recipes.entities.RecipeTag


interface DbApi{

    suspend fun getRecipes(tag: RecipeTag) : List<Recipe>

    suspend fun getRecipes() : List<Recipe>

    fun getRecipesPages(tags : List<RecipeTag>): DataSource.Factory<Int, Recipe>

    suspend fun getRecipeById(id : Long ) : Recipe

    suspend fun insert(recipe: Recipe) : Long

    suspend fun insert(recipeList : List<Recipe>)

    suspend fun loadCategories() : List<Category>

    suspend fun insertCategories(categoryList: List<Category>) : List<Long>

}
