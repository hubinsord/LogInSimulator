package com.hubinsord.loginsimulator.app.data.repository

import com.hubinsord.loginsimulator.app.data.datasource.local.AccountLocalDataSource
import com.hubinsord.loginsimulator.app.data.model.Account
import com.hubinsord.loginsimulator.app.domain.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountLocalDataSource: AccountLocalDataSource
) : AccountRepository{

    override fun getAccounts(): List<Account>{
        return accountLocalDataSource.getAccounts()
    }

    override fun getAccountById(id: Int): Account {
        return accountLocalDataSource.getAccountById(id)
    }

    override fun updateAccount(id: Int, account: Account) {
        accountLocalDataSource.updateAccount(id, account)
    }
}