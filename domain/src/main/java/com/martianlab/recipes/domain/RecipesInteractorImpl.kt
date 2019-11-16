package com.martianlab.recipes.domain

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipesInteractorImpl @Inject constructor(
    val recipesRepository: RecipesRepository
) : RecipesInteractor{

    override suspend fun loadToDb() {
        recipesRepository.loadRecipesToDb()
    }
}