package com.acsoft.savepassword.ui.accounts

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.acsoft.savepassword.R
import com.acsoft.savepassword.application.AppConstants
import com.acsoft.savepassword.data.local.AppDatabase
import com.acsoft.savepassword.data.local.LocalAccountDataSource
import com.acsoft.savepassword.data.model.Account
import com.acsoft.savepassword.databinding.ActivityInsertAccountBinding
import com.acsoft.savepassword.presentation.AccountViewModel
import com.acsoft.savepassword.presentation.AccountViewModelFactory
import com.acsoft.savepassword.repository.AccountRepositoryImpl
import com.acsoft.savepassword.utils.getDateFormat
import com.acsoft.savepassword.utils.upperCase

class InsertAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInsertAccountBinding
    private var favorite:Boolean = false

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

    private var action: String? = null
    private var account: Account? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityInsertAccountBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        init()
        setTitleActivity()
        listeners()
        validateForm()

    }

    private fun setTitleActivity() {
        title = if (action == AppConstants.UPDATE) {
            getString(R.string.screen_edit_account_title)
        } else {
            getString(R.string.screen_new_account_title)
        }
    }

    private fun init() {

        action = intent.getStringExtra(AppConstants.ACTION)

        account = intent.getParcelableExtra(AppConstants.ACCOUNT)
        account.let {
            binding.accountInputEditText.setText(account?.account)
            binding.emailInputEditText.setText(account?.email)
            binding.passwordInputEditText.setText(account?.password)
            binding.webSiteInputEditText.setText(account?.website)
            binding.notesInputEditText.setText(account?.notes)
            favorite = if (account?.favorite==null) false else account?.favorite!!
        }
    }

    private fun listeners() {
        binding.accountInputEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun afterTextChanged(editable: Editable) {
                validateTitle(editable.toString())
                validateForm()
            }
        })

        binding.emailInputEditText.addTextChangedListener(object : TextWatcher {
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

            if (action==AppConstants.INSERT) {
                insertAccount()
            } else if (action==AppConstants.UPDATE) {
                updateAccount()
            }
            finish()
        }
    }

    private fun validateTitle(title: String) {
        if (title.isEmpty()) {
            binding.accountInputLayout.error = getString(R.string.error_message_enter_title)
        } else {
            binding.accountInputLayout.error = null
        }
    }

    private fun validateUsername(username: String) {
        if (username.isEmpty()) {
            binding.emailInputLayout.error = getString(R.string.error_message_enter_username)
        } else {
            binding.emailInputLayout.error = null
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

        val title:String = binding.accountInputEditText.text.toString()
        val username:String = binding.emailInputEditText.text.toString()
        val password:String = binding.passwordInputEditText.text.toString()

        val validate =  title.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()

        binding.saveButton.isEnabled = validate

    }

    private fun insertAccount() {

        val title:String = binding.accountInputEditText.text.toString()
        val username:String = binding.emailInputEditText.text.toString()
        val password:String = binding.passwordInputEditText.text.toString()
        val website:String = binding.webSiteInputEditText.text.toString()
        val notes:String = binding.notesInputEditText.text.toString()
        val date:String = upperCase(getDateFormat())

        val account = Account(
            0, title,
            username, password, website, notes, favorite, date
        )

        viewModel.insertAccount(account)
    }

    private fun updateAccount() {

        val title:String = binding.accountInputEditText.text.toString()
        val username:String = binding.emailInputEditText.text.toString()
        val password:String = binding.passwordInputEditText.text.toString()
        val website:String = binding.webSiteInputEditText.text.toString()
        val notes:String = binding.notesInputEditText.text.toString()
        val date:String = upperCase(getDateFormat())

        val update = Account(
             account!!.id,title,username,password,website,notes,favorite,date)

        viewModel.updateAccount(update)

        val intent = Intent()
        intent.putExtra(AppConstants.ACCOUNT,update)
        setResult(Activity.RESULT_OK, intent)

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }


}