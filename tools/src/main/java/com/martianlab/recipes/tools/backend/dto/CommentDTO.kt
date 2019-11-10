package com.martianlab.recipes.tools.backend.dto

import com.google.gson.annotations.SerializedName

internal class CommentDTO(
    @SerializedName("Author")
    val author : String, // "Ольга Владимировна",
    @SerializedName("Created")
    val  createdAt : String, //"2015-07-26 14:19:52",
    @SerializedName("Id")
    val id : Long, // "58679",
    @SerializedName("ParentId")
    val parentId : Long, // "0",
    @SerializedName("RateUp")
    val rateUp : Int, // "3",
    @SerializedName("RateDown")
    val rateDown : Int, // "0",
    @SerializedName("Text")
    val text : String, // "Попробовала приготовить- очень вкусно получилось, прикупила бульон в желе и гренки... Я только немного твердого тертого сыра добавила в самом конце готовки.",
    @SerializedName("UserPic")
    val userPic : String?,
    @SerializedName("SelfCommentRate")
    val selfCommentRate : Int
)