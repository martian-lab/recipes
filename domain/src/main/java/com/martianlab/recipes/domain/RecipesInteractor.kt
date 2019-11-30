package com.martianlab.recipes.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.martianlab.recipes.entities.Category
import com.martianlab.recipes.entities.Recipe
import com.martianlab.recipes.entities.RecipeIngredient
import kotlinx.coroutines.flow.Flow

interface RecipesInteractor {
    suspend fun loadToDb()
    suspend fun getCategories() : List<Category>
    suspend fun loadToDbFlow() : Flow<String>
    suspend fun getRecipes() : List<Recipe>
    suspend fun getRecipe(id:Long) : Recipe?
    fun getRecipesPaged(category: Category) : LiveData<PagedList<Recipe>>

    suspend fun searchIngredients(contains: String): List<RecipeIngredient>
    suspend fun searchRecipes(contains: String): List<Recipe>
    suspend fun setFavorite(recipe: Recipe)
    suspend fun removeFavorite(recipe: Recipe)
    suspend fun getFavorites(): List<Recipe>
}