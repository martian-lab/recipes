package com.martianlab.recipes.tools.db.converter

import androidx.room.TypeConverter
import java.util.*

class DateTimeConverter {

    @TypeConverter
    fun dateToUnixtime(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun unixtimeToDate(unixtime : Long): Date {
        return Date(unixtime)
    }
}
