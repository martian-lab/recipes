package com.martianlab.recipes.domain

import com.martianlab.recipes.entities.Recipe
import com.martianlab.recipes.entities.Result

interface RecipesRepository {

    suspend fun loadRecipes(count : Int, offset : Int ) : Result<List<Recipe>>
}