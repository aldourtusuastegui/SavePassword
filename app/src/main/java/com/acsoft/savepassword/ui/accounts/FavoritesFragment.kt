package com.acsoft.savepassword.ui.accounts

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.acsoft.savepassword.R
import com.acsoft.savepassword.data.local.AppDatabase
import com.acsoft.savepassword.data.local.LocalAccountDataSource
import com.acsoft.savepassword.data.model.Account
import com.acsoft.savepassword.databinding.FragmentFavoritesBinding
import com.acsoft.savepassword.presentation.AccountViewModel
import com.acsoft.savepassword.presentation.AccountViewModelFactory
import com.acsoft.savepassword.repository.AccountRepositoryImpl
import com.acsoft.savepassword.ui.adapters.AccountAdapter

class FavoritesFragment : Fragment(),AccountAdapter.OnAccountClickListener {

    private lateinit var binding: FragmentFavoritesBinding

    private lateinit var adapter: AccountAdapter

    private val viewModel by viewModels<AccountViewModel> {
        AccountViewModelFactory(AccountRepositoryImpl(LocalAccountDataSource(AppDatabase.getDatabase(requireContext()).AccountDao())))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = AccountAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoritesBinding.bind(view)

        showFavorites()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    private fun showFavorites() {
        binding.rvFavorites.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavorites.addItemDecoration(
                DividerItemDecoration(
                        requireContext(),
                        DividerItemDecoration.VERTICAL
                )
        )
        binding.rvFavorites.adapter = adapter

        viewModel.getFavoritesList().observe(viewLifecycleOwner, { accountList ->
            accountList.let {

                adapter.setAccountList(accountList)

                if (accountList.isEmpty()) {
                    binding.rvFavorites.visibility = View.GONE
                    binding.tvEmptyMessage.visibility = View.VISIBLE
                } else {
                    binding.rvFavorites.visibility = View.VISIBLE
                    binding.tvEmptyMessage.visibility = View.GONE
                }

            }
        })

    }

    override fun onAccountClick(account: Account) {
        Log.d("TAG","click")
    }

}