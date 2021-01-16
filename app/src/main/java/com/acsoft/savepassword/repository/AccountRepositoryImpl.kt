package com.acsoft.savepassword.repository

import androidx.lifecycle.LiveData
import com.acsoft.savepassword.data.local.LocalAccountDataSource
import com.acsoft.savepassword.data.model.Account

class AccountRepositoryImpl(private val localDataSource: LocalAccountDataSource): AccountRepository {

    override suspend fun getAllAccounts(): LiveData<List<Account>> {
       return localDataSource.getAllAccounts()
    }

    override suspend fun insertAccount(account: Account) {
        localDataSource.insertAccount(account)
    }
}