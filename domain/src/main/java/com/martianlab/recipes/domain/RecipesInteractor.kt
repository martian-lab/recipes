package com.martianlab.recipes.domain

interface RecipesInteractor {
    suspend fun loadToDb()
}