package com.martianlab.recipes.domain

import com.martianlab.recipes.entities.Category

interface RecipesInteractor {
    suspend fun loadToDb()
    suspend fun getCategories() : List<Category>
}