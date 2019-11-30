package com.martianlab.recipes.data.db

import androidx.paging.DataSource
import com.martianlab.recipes.entities.Category
import com.martianlab.recipes.entities.Recipe
import com.martianlab.recipes.entities.RecipeIngredient
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

    suspend fun searchIngredients( contains : String ) : List<RecipeIngredient>

    suspend fun searchRecipes( contains : String ) : List<Recipe>

    suspend fun setFavorite(recipe: Recipe)

    suspend fun removeFavorite(recipe: Recipe)

    suspend fun getFavorites(): List<Recipe>

    suspend fun getRecipesByIngredient(ingredient: RecipeIngredient) : List<Recipe>


}
