package com.martianlab.recipes.domain

import com.martianlab.recipes.entities.Category
import com.martianlab.recipes.entities.Recipe
import com.martianlab.recipes.entities.RecipeIngredient
import com.martianlab.recipes.entities.Result
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {

    suspend fun getRecipesFromBackend(count : Int, offset : Int ) : Result<List<Recipe>>

    suspend fun insertRecipes(recipes: List<Recipe>)

    suspend fun loadRecipes() : List<Recipe>

    suspend fun getRecipe( id : Long ) : Recipe

    suspend fun getRecipesByIngredient( ingredients: List<RecipeIngredient> ) : List<Recipe>

    suspend fun loadRecipesToDb()

    suspend fun loadCategoriesFromDb() : List<Category>

    suspend fun loadRecipesToDbFlow() : Flow<String>
}