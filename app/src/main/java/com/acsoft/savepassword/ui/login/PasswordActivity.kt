package com.acsoft.savepassword.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.acsoft.savepassword.R
import com.acsoft.savepassword.databinding.ActivityPasswordBinding
import com.acsoft.savepassword.utils.SharedPreferences

class PasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPasswordBinding
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = SharedPreferences(this)

        listeners()
        validateForm()

    }

    private fun listeners() {
        binding.newPasswordInputEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                validateNewPassword(s.toString())
                validateForm()
            }
        })

        binding.confirmPasswordInputEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                validateConfirmPassword(s.toString())
                validateForm()
            }
        })

        binding.savePasswordBtn.setOnClickListener {

            val password = binding.newPasswordInputEditText.text.toString()
            sharedPreferences.save(SharedPreferences.PASSWORD,password)

            finish()
            startActivity(Intent(this,LoginActivity::class.java))

        }

    }


    private fun validateNewPassword(password: String) {
        if (password.length!=4) {
            binding.newPasswordInputLayout.error = getString(R.string.error_message_enter_password)
        } else {
            binding.newPasswordInputLayout.error = null
        }
    }

    private fun validateConfirmPassword(password: String) {
        if (password.length!=4) {
            binding.confirmPasswordInputLayout.error = getString(R.string.error_message_enter_password)
        } else {
            binding.confirmPasswordInputLayout.error = null
        }
    }

    private fun validateForm() {

        val newPassword:String = binding.newPasswordInputEditText.text.toString()
        val confirmPassword:String = binding.confirmPasswordInputEditText.text.toString()

        val validate =  (newPassword.length==4 && confirmPassword.length==4)
                && (newPassword == confirmPassword)

        binding.savePasswordBtn.isEnabled = validate

    }



}