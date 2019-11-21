package com.martianlab.recipes.domain

import com.martianlab.recipes.entities.Category
import com.martianlab.recipes.entities.Recipe
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipesInteractorImpl @Inject constructor(
    val recipesRepository: RecipesRepository
) : RecipesInteractor{

    override suspend fun loadToDb() {
        recipesRepository.loadRecipesToDb()
    }

    override suspend fun getCategories(): List<Category> {
        return recipesRepository.loadCategoriesFromDb()
    }

    override suspend fun loadToDbFlow(): Flow<String> {
        return recipesRepository.loadRecipesToDbFlow()
    }

    override suspend fun getRecipes(): List<Recipe> {
        return recipesRepository.loadRecipes()
    }
}