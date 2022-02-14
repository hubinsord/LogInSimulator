package com.hubinsord.loginsimulator.app.data.model

import java.text.DateFormat

data class Account(
    val userName: String,
    val password: String,
    var lastLoginDate: Long? = null
) {
    val lastLoginDateFormatted: String?
        get() = DateFormat.getDateTimeInstance().format(lastLoginDate)
}