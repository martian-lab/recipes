package com.martianlab.recipes.tools.backend.dto

import com.google.gson.annotations.SerializedName

class RecipeCookingDTO (
    @SerializedName("Id")
    val id : Long,
    @SerializedName("RecipeId")
    val recipeId : Long,
    @SerializedName("Image")
    val imageURL : String,
    @SerializedName("Description")
    val description : String,
    @SerializedName("Position")
    val position : Int
)