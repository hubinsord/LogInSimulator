package com.hubinsord.loginsimulator.core.data.model

import java.text.DateFormat
import java.util.*

data class Account(
    val userName: String,
    val password: String,
    var lastLoginDate: Long? = null
) {
    val lastLoginDateFormatted: String?
        get() = DateFormat.getDateTimeInstance().format(lastLoginDate)
}