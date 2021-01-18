package com.acsoft.savepassword.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.acsoft.savepassword.data.model.Account
import com.acsoft.savepassword.repository.AccountRepository

class AccountViewModel(private val accountRepository: AccountRepository) : ViewModel() {

  /*  private suspend fun getPostalCode(address: String): LiveData<List<Account>> {
        // DON'T DO THIS
        return accountRepository.getAllAccounts()
    }
*/
}