package com.quantrics.finch.global

object Constant {

    const val DATE_PARSE = "yyyy-MM-dd"
    const val TIME_PARSE = "HH:mm:ss"
    const val DATE_TIME_PARSE = "$DATE_PARSE'T'$TIME_PARSE.SSS"
    const val DATE_FORMAT = "MMMM dd, yyyy"
    const val TIME_FORMAT = "hh:mm aa"
    const val DATE_TIME_FORMAT = "$DATE_FORMAT $TIME_FORMAT"

    const val APP_CENTER_API = "062d6ddd-e12c-4ad3-b6ff-85531a052ef9"

    const val APP_UPDATE_REQUEST = 10000

    const val PERMISSION_LOCATION_REQUEST = 1001

    const val APP_AUTH_REQUEST = 2001
}