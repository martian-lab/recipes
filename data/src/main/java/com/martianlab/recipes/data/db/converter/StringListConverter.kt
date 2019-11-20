package com.martianlab.recipes.tools.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringListConverter {

    @TypeConverter
    fun listToString(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun stringToList(list : String): List<String> {
        return Gson().fromJson(list, object : TypeToken<List<String>>(){}.type)
    }
}
