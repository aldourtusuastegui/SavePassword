package com.acsoft.savepassword.repository

import com.acsoft.savepassword.data.local.LocalAccountDataSource
import com.acsoft.savepassword.data.model.Account
import kotlinx.coroutines.flow.Flow

class AccountRepositoryImpl(private val localDataSource: LocalAccountDataSource): AccountRepository {

    override fun getAllAccounts(): Flow<List<Account>> {
       return localDataSource.getAllAccounts()
    }

    override suspend fun insertAccount(account: Account) {
        localDataSource.insertAccount(account)
    }

    override suspend fun updateAccount(account: Account) {
        localDataSource.updateAccount(account)
    }

    override suspend fun setFavorite(id: Int, isFavorite: Boolean) {
        localDataSource.setFavorite(id,isFavorite)
    }

    override suspend fun deleteAccount(account: Account) {
        localDataSource.deleteAccount(account)
    }

}