package com.hubinsord.loginsimulator.core.data.model

import java.util.*

data class Account(
    val userName: String,
    val password: String,
    val lastLoginDate: Date? = null
) {
}