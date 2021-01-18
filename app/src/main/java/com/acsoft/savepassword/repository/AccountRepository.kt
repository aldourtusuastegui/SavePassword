package com.acsoft.savepassword.repository

import com.acsoft.savepassword.data.model.Account
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

    fun getAllAccounts() : Flow<List<Account>>
    suspend fun insertAccount(account: Account)

}