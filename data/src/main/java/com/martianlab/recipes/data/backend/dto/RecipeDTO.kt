package com.martianlab.recipes.tools.backend.dto

import com.google.gson.annotations.SerializedName

class RecipeDTO(
    @SerializedName("Id")
    val id : Int,
    @SerializedName("Url")
    val url : String, //"/recipe/3517",
    @SerializedName("Name")
    val name : String,
    @SerializedName("Complexity")
    val complexity : Int,
    @SerializedName("NumberPersons")
    val numberPersons : Int,
    @SerializedName("Image")
    val imageURL : String,
    @SerializedName("ViewImage")
    val viewImageURL : String,
    @SerializedName("Description")
    val description : String,// "",
    @SerializedName("Comment")
    val comments : List<CommentDTO>,
    @SerializedName("Rating")
    val rating : RatingDTO?
)

class RatingDTO(
    @SerializedName("Rate")
    val rate : Int,
    @SerializedName("Votes")
    val votes : Int
)
