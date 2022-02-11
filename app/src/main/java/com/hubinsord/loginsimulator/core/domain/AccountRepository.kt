package com.hubinsord.loginsimulator.core.domain

import com.hubinsord.loginsimulator.core.data.model.Account

interface AccountRepository {

    fun getAccounts() : List<Account>
}