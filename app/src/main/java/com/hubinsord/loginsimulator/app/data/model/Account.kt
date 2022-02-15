package com.hubinsord.loginsimulator.app.data.model

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

data class Account(
    val userName: String,
    val password: String,
    var lastLoginDate: Long? = null
) {
    val lastLoginDateFormatted: String?
        get() = DateFormat.getDateInstance(DateFormat.LONG).format(lastLoginDate)
    }