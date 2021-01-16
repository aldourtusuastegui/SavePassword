package com.acsoft.savepassword.data.local

import androidx.lifecycle.LiveData
import com.acsoft.savepassword.data.model.Account

class LocalAccountDataSource(private val accountDao: AccountDao) {

    suspend fun getAllAccounts() : LiveData<List<Account>> {
        return accountDao.getAllAccounts()
    }

    suspend fun insertAccount(account: Account) {
        accountDao.insertAccount(account)
    }
}