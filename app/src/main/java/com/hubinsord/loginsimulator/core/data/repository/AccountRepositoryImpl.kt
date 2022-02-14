package com.hubinsord.loginsimulator.core.data.repository

import com.hubinsord.loginsimulator.app.datasource.local.AccountLocalDataSource
import com.hubinsord.loginsimulator.core.data.model.Account
import com.hubinsord.loginsimulator.core.domain.AccountRepository
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