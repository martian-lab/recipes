package com.martianlab.recipes.domain

import com.martianlab.recipes.entities.Recipe


/**
 * REST API access points
 */
interface BackendApi {

    suspend fun recipeSearch(categoryId : Long, recipeId : Long, count : Int, offset : Int ) : List<Recipe>

}
