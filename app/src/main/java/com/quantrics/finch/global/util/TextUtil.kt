package com.quantrics.finch.global.util

import androidx.databinding.InverseMethod
import com.quantrics.finch.global.Constant
import java.text.SimpleDateFormat
import java.util.*

object TextUtil {

    @JvmStatic
    @InverseMethod("stringToDate")
    fun dateToString(date: Date?): String? =
        formatDate(date, Constant.DATE_FORMAT)

    @JvmStatic
    fun stringToDate(date: String): Date? =
        parseDate(date, Constant.DATE_FORMAT)

    @JvmStatic
    @InverseMethod("stringToTime")
    fun timeToString(date: Date?): String? =
        formatDate(date, Constant.TIME_FORMAT)

    @JvmStatic
    fun stringToTime(date: String): Date? =
        parseDate(date, Constant.TIME_FORMAT)

    @JvmStatic
    @InverseMethod("stringToDateTime")
    fun dateTimeToString(date: Date?): String? =
        formatDate(date, Constant.DATE_TIME_FORMAT)

    @JvmStatic
    fun stringToDateTime(date: String): Date? =
        parseDate(date, Constant.DATE_TIME_FORMAT)

    fun formatDate(date: Date?, format: String): String? =
        date?.let { SimpleDateFormat(format, Locale.getDefault()).format(it) }

    fun parseDate(date: String?, format: String): Date? =
        date?.let { SimpleDateFormat(format, Locale.getDefault()).parse(it) }

    @JvmStatic
    @InverseMethod("stringToInteger")
    fun integerToString(int: Int?): String? = int?.toString()

    @JvmStatic
    fun stringToInteger(int: String?): Int? = int?.toIntOrNull()
}
