package com.acsoft.savepassword.data.local

import com.acsoft.savepassword.data.model.Account
import kotlinx.coroutines.flow.Flow

class LocalAccountDataSource(private val accountDao: AccountDao) {

    fun getAllAccounts() : Flow<List<Account>> {
        return accountDao.getAllAccounts()
    }

    suspend fun insertAccount(account: Account) {
        accountDao.insertAccount(account)
    }
}