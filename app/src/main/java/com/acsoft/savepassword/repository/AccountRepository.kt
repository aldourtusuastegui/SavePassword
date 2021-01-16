package com.acsoft.savepassword.repository

import androidx.lifecycle.LiveData
import com.acsoft.savepassword.data.model.Account

interface AccountRepository {

    suspend fun getAllAccounts() : LiveData<List<Account>>
    suspend fun insertAccount(account: Account)

}