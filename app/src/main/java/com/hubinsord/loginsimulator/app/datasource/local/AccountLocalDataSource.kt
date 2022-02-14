package com.hubinsord.loginsimulator.app.datasource.local

import com.hubinsord.loginsimulator.core.data.model.Account

class AccountLocalDataSource {
    private var accounts = listOf<Account>(Account("nextapps", "rekr") )

    fun getAccounts(): List<Account> = accounts
}