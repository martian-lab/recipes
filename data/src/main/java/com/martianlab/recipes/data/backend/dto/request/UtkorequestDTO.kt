package com.martianlab.recipes.tools.backend.dto.request

import com.google.gson.annotations.SerializedName

internal class UtkorequestDTO(

    @SerializedName("Head")
    val head: Head,

    @SerializedName("Body")
    val body: Any?
) {
    class Head(

        @SerializedName("Method")
        val method: String,

        @SerializedName("Username")
        val userLogin: String,

        @SerializedName("Password")
        val userPassword: String,

        @SerializedName("AuthToken")
        val authToken: String,

        @SerializedName("Client")
        val client: String,

        @SerializedName("DeviceId")
        val deviceId: String,

        @SerializedName("AdvertisingId")
        val advertisingId: String,

        @SerializedName("AppsFlyerId")
        val appsFlyerId: String,

        @SerializedName("MarketingPartnerKey")
        val marketingPartnerKey: String
    ) {
        @SerializedName("SessionToken")
        var sessionToken = ""
    }
}