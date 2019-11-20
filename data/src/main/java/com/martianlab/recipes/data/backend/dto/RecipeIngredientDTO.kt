package com.martianlab.recipes.tools.backend.dto

import com.google.gson.annotations.SerializedName

class RecipeIngredientDTO(
    @SerializedName("Id")
    val id : Long,
    @SerializedName("RecipeId")
    val recipeId : Long,
    @SerializedName("Name")
    val name : String,
    @SerializedName("HowMany")
    val quantity : String,
    @SerializedName("HowManyUnit")
    val measureUnit : String,
    @SerializedName("Position")
    val position : Int
)


