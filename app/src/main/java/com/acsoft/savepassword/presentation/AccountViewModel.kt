package com.acsoft.savepassword.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.acsoft.savepassword.data.model.Account
import com.acsoft.savepassword.repository.AccountRepository
import kotlinx.coroutines.launch

class AccountViewModel(private val accountRepository: AccountRepository) : ViewModel() {

    fun getAccountList() = accountRepository.getAllAccounts().asLiveData()

    fun insertAccount(account: Account) {
        viewModelScope.launch {
            accountRepository.insertAccount(account)
        }
    }

}


class AccountViewModelFactory(private val repo: AccountRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(AccountRepository::class.java).newInstance(repo)
    }
}