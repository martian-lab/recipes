package com.martianlab.recipes.data

import android.content.SharedPreferences
import com.martianlab.recipes.domain.*
import com.martianlab.recipes.entities.Recipe
import com.martianlab.recipes.entities.Result
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RecipesRepositoryImpl @Inject constructor(
    private val dbApi: DbApi,
    private val backendApi: BackendApi,
    private val preferences: SharedPreferences
) : RecipesRepository {


    override suspend fun loadRecipes(count: Int, offset: Int): Result<List<Recipe>> {
        return backendApi.recipeSearch(0L, 0L, count, offset)
    }
}


