package com.martianlab.recipes.domain

import com.martianlab.recipes.entities.Category
import kotlinx.coroutines.flow.Flow

interface RecipesInteractor {
    suspend fun loadToDb()
    suspend fun getCategories() : List<Category>
    suspend fun loadToDbFlow() : Flow<String>
}