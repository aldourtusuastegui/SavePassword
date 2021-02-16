package com.acsoft.savepassword.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.acsoft.savepassword.databinding.ActivityLoginBinding
import com.acsoft.utils.clickVibration
import com.acsoft.utils.wrongPasswordVibration

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button0.setOnClickListener {
            clickVibration(this)
        }

        binding.button2.setOnClickListener {
            wrongPasswordVibration(this)
        }

    }
}