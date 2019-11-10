package com.martianlab.recipes.tools.backend

import com.martianlab.recipes.domain.BackendApi
import com.martianlab.recipes.entities.Recipe

internal object  BackendImpl : BackendApi {

    override suspend fun recipeSearch(
        categoryId: Long,
        recipeId: Long,
        count: Int,
        offset: Int
    ): List<Recipe> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}