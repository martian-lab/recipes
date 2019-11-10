package com.martianlab.recipes.tools.backend.dto.response

import com.google.gson.annotations.SerializedName

internal class UtkerrorDTO(

    @SerializedName("Code")
    val code: Int,

    @SerializedName("Description")
    val message: String
)