package com.martianlab.recipes.tools.backend.dto.response

import com.google.gson.annotations.SerializedName

internal class UtkoresponseDTO<B>(
    @SerializedName("Head")
    val head: Head,

    @SerializedName("Body")
    val body: B
) {
    class Head(
        @SerializedName("SessionToken")
        val accessToken: String?,

        @SerializedName("SessionGroups")
        val sessionGroups: Array<String>?
    )

    class BaseBody(
        @SerializedName("ErrorList")
        val errors: Array<UtkerrorDTO>?
    )
}