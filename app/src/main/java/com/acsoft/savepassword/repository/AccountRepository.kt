package com.acsoft.savepassword.repository

import com.acsoft.savepassword.data.model.Account
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

    fun getAllAccounts() : Flow<List<Account>>

    fun getFavorites() : Flow<List<Account>>

    fun searchAccount(account: String) : Flow<List<Account>>

    suspend fun insertAccount(account: Account)

    suspend fun updateAccount(account: Account)

    suspend fun setFavorite(id: Int,isFavorite: Boolean)

    suspend fun deleteAccount(account: Account)

}