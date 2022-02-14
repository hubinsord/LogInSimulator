package com.hubinsord.loginsimulator.app.datasource.local

import com.hubinsord.loginsimulator.core.data.model.Account

class AccountLocalDataSource {
    private var accounts = mutableListOf<Account>(Account("nextapps", "rekr"))

    fun getAccounts(): List<Account> = accounts

    fun getAccountById(id: Int) = accounts[id]

    fun updateAccount(id:Int, account: Account){
        accounts.add(id, account)
    }
}