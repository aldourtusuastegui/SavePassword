package com.acsoft.savepassword.ui.accounts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.acsoft.savepassword.R
import com.acsoft.savepassword.databinding.ActivityInsertAccountBinding
import com.acsoft.utils.hideKeyboard

class InsertAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInsertAccountBinding

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


        }

    }


    private fun validateTitle(title:String) {
        if (title.isEmpty()) {
            binding.titleInputLayout.error = getString(R.string.error_message_enter_title)
        } else {
            binding.titleInputLayout.error = null
        }
    }

    private fun validateUsername(username:String) {
        if (username.isEmpty()) {
            binding.usernameInputLayout.error = getString(R.string.error_message_enter_username)
        } else {
            binding.usernameInputLayout.error = null
        }
    }

    private fun validatePassword(password:String) {
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


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }


}