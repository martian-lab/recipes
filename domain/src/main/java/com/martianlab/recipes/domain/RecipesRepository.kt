package com.martianlab.recipes.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.martianlab.recipes.entities.*
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {

    suspend fun getRecipesFromBackend(count : Int, offset : Int ) : Result<List<Recipe>>

    suspend fun insertRecipes(recipes: List<Recipe>)

    suspend fun loadRecipes() : List<Recipe>

    suspend fun getRecipe( id : Long ) : Recipe?

    suspend fun getRecipesByIngredients( ingredients: List<RecipeIngredient> ) : List<Recipe>

    suspend fun loadRecipesToDb()

    suspend fun loadCategoriesFromDb() : List<Category>

    suspend fun loadRecipesToDbFlow() : Flow<String>

    fun loadRecipesPaged(tags : List<RecipeTag>): LiveData<PagedList<Recipe>>

    suspend fun searchIngredients( contains : String ) : List<RecipeIngredient>

    suspend fun searchRecipes( contains : String ) : List<Recipe>

    suspend fun setFavorite(recipe: Recipe)

    suspend fun removeFavorite(recipe: Recipe)

    suspend fun getFavorites(): List<Recipe>

}