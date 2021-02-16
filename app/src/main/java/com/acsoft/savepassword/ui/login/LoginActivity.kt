package com.acsoft.savepassword.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.acsoft.enums.Number
import com.acsoft.savepassword.MainActivity
import com.acsoft.savepassword.databinding.ActivityLoginBinding
import com.acsoft.utils.SharedPreferences
import com.acsoft.utils.clickVibration
import com.acsoft.utils.wrongPasswordVibration

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences
    private var touchValue = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreferences = SharedPreferences(this)


        listeners()


    }

    private fun listeners() {

        binding.buttonNumberZero.setOnClickListener {
            clickVibration(this)
            touchValue += Number.ZERO.ordinal
            Log.d("NEW",touchValue)
            validatePassword()
        }

        binding.buttonNumberOne.setOnClickListener {
            clickVibration(this)
            touchValue += Number.ONE.ordinal
            Log.d("NEW",touchValue)
            validatePassword()
        }

        binding.buttonNumberTwo.setOnClickListener {
            clickVibration(this)
            touchValue += Number.TWO.ordinal
            Log.d("NEW",touchValue)
            validatePassword()
        }

        binding.buttonNumberThree.setOnClickListener {
            clickVibration(this)
            touchValue += Number.THREE.ordinal
            Log.d("NEW",touchValue)
            validatePassword()
        }

        binding.buttonNumberFour.setOnClickListener {
            clickVibration(this)
            touchValue += Number.FOUR.ordinal
            Log.d("NEW",touchValue)
            validatePassword()
        }

        binding.buttonNumberFive.setOnClickListener {
            clickVibration(this)
            touchValue += Number.FIVE.ordinal
            Log.d("NEW",touchValue)
            validatePassword()
        }

        binding.buttonNumberSix.setOnClickListener {
            clickVibration(this)
            touchValue += Number.SIX.ordinal
            validatePassword()
        }

        binding.buttonNumberSeven.setOnClickListener {
            clickVibration(this)
            touchValue += Number.SEVEN.ordinal
            validatePassword()
        }

        binding.buttonNumberEight.setOnClickListener {
            clickVibration(this)
            touchValue += Number.EIGHT.ordinal
            validatePassword()
        }

        binding.buttonNumberNine.setOnClickListener {
            clickVibration(this)
            touchValue += Number.NINE.ordinal
            validatePassword()
        }

    }

    private fun validatePassword() {
       val password = sharedPreferences.getValueString(SharedPreferences.PASSWORD)
       if (password.equals(touchValue)) {
           finish()
           startActivity(Intent(this,MainActivity::class.java))
       }
    }
}