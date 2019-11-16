package com.martianlab.recipes.tools.backend

import com.martianlab.recipes.tools.backend.dto.response.RecipeSearchResponseBodyDTO
import com.martianlab.recipes.tools.backend.dto.response.UtkoresponseDTO
import com.martianlab.recipes.entities.Result
import retrofit2.http.*

internal interface Caller {


    @Headers(
        "Content-Type: application/json"
    )
    @GET("getTripList")
    suspend fun recipeSearch(
        @Header("x-mob-sgm") androidId: String,
        @Header("x-mob-method") method: String,
        @Query("request") request: String
    ): Result<UtkoresponseDTO<RecipeSearchResponseBodyDTO>>
}