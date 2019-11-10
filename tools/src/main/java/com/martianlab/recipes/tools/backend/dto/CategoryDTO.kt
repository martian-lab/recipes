package com.martianlab.recipes.tools.backend.dto

import com.google.gson.annotations.SerializedName

internal class CategoryDTO(

    @SerializedName("Id")
    val id : Int,
    @SerializedName("Category")
    val category : String,
    @SerializedName("Sort")
    val sort : Int
)