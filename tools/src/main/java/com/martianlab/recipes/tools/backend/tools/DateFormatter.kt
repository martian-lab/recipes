package com.utkonos.utkomobile.core_network_impl.data.util

import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {

    fun format(date : Date?) : String{
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        return date?.let {
            simpleDateFormat.format(it)
        } ?: ""
    }

}