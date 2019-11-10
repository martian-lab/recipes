package com.martianlab.recipes.tools.backend

import com.martianlab.recipes.tools.backend.dto.response.RecipeSearchResponseBodyDTO
import com.martianlab.recipes.tools.backend.dto.response.UtkoresponseDTO
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

internal interface Caller {


    @GET("/api/rest/?")
    fun recipeSearch(
        @Header("x-mob-sgm") androidId: String,
        @Header("x-mob-method") method: String,
        @Query("request") request: String
    ): UtkoresponseDTO<RecipeSearchResponseBodyDTO>
}