package com.hubinsord.loginsimulator.app.domain

import com.hubinsord.loginsimulator.app.data.model.Account

interface AccountRepository {

    fun getAccounts() : List<Account>
    fun getAccountById(id: Int): Account
    fun updateAccount(id: Int, account: Account)
}