package com.acsoft.savepassword.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.acsoft.savepassword.R
import com.acsoft.savepassword.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}