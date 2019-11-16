package com.martianlab.recipes.tools.backend.dto

import com.google.gson.annotations.SerializedName

class CategoryDTO(

    @SerializedName("Id")
    val id : Long,
    @SerializedName("Category")
    val category : String,
    @SerializedName("Sort")
    val sort : Int
)