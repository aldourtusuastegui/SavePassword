package com.acsoft.savepassword.ui.accounts

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.acsoft.savepassword.R
import com.acsoft.savepassword.data.local.AppDatabase
import com.acsoft.savepassword.data.local.LocalAccountDataSource
import com.acsoft.savepassword.data.model.Account
import com.acsoft.savepassword.databinding.ActivityInsertAccountBinding
import com.acsoft.savepassword.presentation.AccountViewModel
import com.acsoft.savepassword.presentation.AccountViewModelFactory
import com.acsoft.savepassword.repository.AccountRepositoryImpl
import com.acsoft.utils.getDate
import java.util.*


class InsertAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInsertAccountBinding

    private val viewModel by viewModels<AccountViewModel> {
        AccountViewModelFactory(
            AccountRepositoryImpl(
                LocalAccountDataSource(
                    AppDatabase.getDatabase(
                        this
                    ).AccountDao()
                )
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityInsertAccountBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.titleInputEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun afterTextChanged(editable: Editable) {
                validateTitle(editable.toString())
                validateForm()
            }
        })

        binding.usernameInputEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun afterTextChanged(editable: Editable) {
                validateUsername(editable.toString())
                validateForm()
            }
        })

        binding.passwordInputEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun afterTextChanged(editable: Editable) {
                validatePassword(editable.toString())
                validateForm()
            }
        })


        binding.saveButton.setOnClickListener {
            insertAccount()
            finish()

            val titleAccount = binding.titleInputEditText.text.toString()
            Toast.makeText(this, "Se guardo la cuenta de $titleAccount", Toast.LENGTH_LONG).show()
        }

    }


    private fun validateTitle(title: String) {
        if (title.isEmpty()) {
            binding.titleInputLayout.error = getString(R.string.error_message_enter_title)
        } else {
            binding.titleInputLayout.error = null
        }
    }

    private fun validateUsername(username: String) {
        if (username.isEmpty()) {
            binding.usernameInputLayout.error = getString(R.string.error_message_enter_username)
        } else {
            binding.usernameInputLayout.error = null
        }
    }

    private fun validatePassword(password: String) {
        if (password.isEmpty()) {
            binding.passwordInputLayout.error = getString(R.string.error_message_enter_password)
        } else {
            binding.passwordInputLayout.error = null
        }
    }

    private fun validateForm() {

        val title:String = binding.titleInputEditText.text.toString()
        val username:String = binding.usernameInputEditText.text.toString()
        val password:String = binding.passwordInputEditText.text.toString()

        val validate =  title.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()

        binding.saveButton.isEnabled = validate

    }

    private fun insertAccount() {

        val title:String = binding.titleInputEditText.text.toString()
        val username:String = binding.usernameInputEditText.text.toString()
        val password:String = binding.passwordInputEditText.text.toString()
        val website:String = binding.webSiteInputEditText.text.toString()
        val notes:String = binding.notesInputEditText.text.toString()
        val date:String = getDate()

        val account = Account(
            0, title,
            username, password, website, notes, false, date
        )

        viewModel.insertAccount(account)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }


}