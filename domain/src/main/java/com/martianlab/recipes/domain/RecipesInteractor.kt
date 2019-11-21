package com.martianlab.recipes.domain

import com.martianlab.recipes.entities.Category
import com.martianlab.recipes.entities.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipesInteractor {
    suspend fun loadToDb()
    suspend fun getCategories() : List<Category>
    suspend fun loadToDbFlow() : Flow<String>
    suspend fun getRecipes() : List<Recipe>
}