package com.acsoft.savepassword.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.acsoft.savepassword.R
import com.acsoft.savepassword.databinding.ActivityPasswordBinding
import com.acsoft.utils.SharedPreferences

class PasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPasswordBinding
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPasswordBinding.inflate(layoutInflater)
        binding.root

        sharedPreferences = SharedPreferences(this)

        sharedPreferences.save(SharedPreferences.FIRST_TIME,true)


    }



}