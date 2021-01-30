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

    suspend fun updateAccount(account: Account) {
        accountDao.updateAccount(account)
    }

    suspend fun setFavorite(id: Int,isFavorite: Boolean) {
        accountDao.setFavorite(id,isFavorite)
    }

    suspend fun deleteAccount(account: Account) {
        accountDao.deleteAccount(account)
    }
}