package com.acsoft.savepassword.ui.passwords

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.acsoft.savepassword.R
import com.acsoft.savepassword.data.local.AppDatabase
import com.acsoft.savepassword.data.local.LocalAccountDataSource
import com.acsoft.savepassword.data.model.Account
import com.acsoft.savepassword.databinding.FragmentPasswordsBinding
import com.acsoft.savepassword.presentation.AccountViewModel
import com.acsoft.savepassword.presentation.AccountViewModelFactory
import com.acsoft.savepassword.repository.AccountRepositoryImpl

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



/**
 * A simple [Fragment] subclass.
 * Use the [PasswordsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PasswordsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentPasswordsBinding

    private val viewModel by viewModels<AccountViewModel> {
        AccountViewModelFactory(AccountRepositoryImpl(LocalAccountDataSource(AppDatabase.getDatabase(requireContext()).AccountDao())))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_passwords, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPasswordsBinding.bind(view)

        insertAccount()

        viewModel.getAccountList().observe(viewLifecycleOwner, { data ->
            data.let {
                Log.d("NEW", data.toString())
            }
            Log.d("NEW",data.size.toString())
        })

    }

    private fun insertAccount() {
        val account = Account(0,"alojaa",
                "jajajajja","sfsfs","sfsf","wrewrr",false)

        viewModel.insertAccount(account)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PasswordsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PasswordsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}