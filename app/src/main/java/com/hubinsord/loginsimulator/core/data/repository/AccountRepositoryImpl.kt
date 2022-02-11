package com.hubinsord.loginsimulator.core.data.repository

import com.hubinsord.loginsimulator.core.data.model.Account
import com.hubinsord.loginsimulator.core.domain.AccountRepository

class AccountRepositoryImpl : AccountRepository{

    override fun getAccounts(): List<Account>{
        return listOf(Account("nextapps", "rekr"))
    }
}