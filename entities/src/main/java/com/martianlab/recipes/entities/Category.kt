package com.martianlab.recipes.entities

data class Category(

    var id : Int = 0,
    val title : String,
    val imageUrl : String?,
    val sort : Int
)