package com.acsoft.savepassword.ui.accounts

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.acsoft.savepassword.R
import com.acsoft.savepassword.application.AppConstants
import com.acsoft.savepassword.data.local.AppDatabase
import com.acsoft.savepassword.data.local.LocalAccountDataSource
import com.acsoft.savepassword.data.model.Account
import com.acsoft.savepassword.databinding.FragmentAccountsBinding
import com.acsoft.savepassword.presentation.AccountViewModel
import com.acsoft.savepassword.presentation.AccountViewModelFactory
import com.acsoft.savepassword.repository.AccountRepositoryImpl
import com.acsoft.savepassword.ui.adapters.AccountAdapter


class PasswordsFragment : Fragment(),AccountAdapter.OnAccountClickListener {

    private lateinit var binding: FragmentAccountsBinding

    private lateinit var adapter: AccountAdapter

    private val viewModel by viewModels<AccountViewModel> {
        AccountViewModelFactory(AccountRepositoryImpl(LocalAccountDataSource(AppDatabase.getDatabase(requireContext()).AccountDao())))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = AccountAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_accounts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAccountsBinding.bind(view)

        showAccounts()

    }

    private fun showAccounts() {
        binding.rvAccount.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAccount.addItemDecoration(
                DividerItemDecoration(
                        requireContext(),
                        DividerItemDecoration.VERTICAL
                )
        )
        binding.rvAccount.adapter = adapter

        viewModel.getAccountList().observe(viewLifecycleOwner, { accountList ->
            accountList.let {

                adapter.setAccountList(accountList)

                if (accountList.isEmpty()) {
                    binding.rvAccount.visibility = View.GONE
                    binding.tvEmptyMessage.visibility = View.VISIBLE
                } else {
                    binding.rvAccount.visibility = View.VISIBLE
                    binding.tvEmptyMessage.visibility = View.GONE
                }

            }
        })
    }

    override fun onAccountClick(account: Account) {
        val intent = Intent(activity,DetailActivity::class.java)
        intent.putExtra(AppConstants.ACCOUNT,account)
        startActivity(intent)
    }
}