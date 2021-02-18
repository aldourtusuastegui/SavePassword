package com.acsoft.savepassword.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.lifecycleScope
import com.acsoft.savepassword.enums.Number
import com.acsoft.savepassword.MainActivity
import com.acsoft.savepassword.R
import com.acsoft.savepassword.databinding.ActivityLoginBinding
import com.acsoft.savepassword.utils.SharedPreferences
import com.acsoft.savepassword.utils.clickVibration
import com.acsoft.savepassword.utils.wrongPasswordVibration
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences
    private var touchValue = String()
    private var countClicks = 0

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
            showCircleColored()
            validatePassword()
        }

        binding.buttonNumberOne.setOnClickListener {
            clickVibration(this)
            touchValue += Number.ONE.ordinal
            showCircleColored()
            validatePassword()
        }

        binding.buttonNumberTwo.setOnClickListener {
            clickVibration(this)
            touchValue += Number.TWO.ordinal
            showCircleColored()
            validatePassword()
        }

        binding.buttonNumberThree.setOnClickListener {
            clickVibration(this)
            touchValue += Number.THREE.ordinal
            showCircleColored()
            validatePassword()
        }

        binding.buttonNumberFour.setOnClickListener {
            clickVibration(this)
            touchValue += Number.FOUR.ordinal
            showCircleColored()
            validatePassword()
        }

        binding.buttonNumberFive.setOnClickListener {
            clickVibration(this)
            touchValue += Number.FIVE.ordinal
            showCircleColored()
            validatePassword()
        }

        binding.buttonNumberSix.setOnClickListener {
            clickVibration(this)
            touchValue += Number.SIX.ordinal
            showCircleColored()
            validatePassword()
        }

        binding.buttonNumberSeven.setOnClickListener {
            clickVibration(this)
            touchValue += Number.SEVEN.ordinal
            showCircleColored()
            validatePassword()
        }

        binding.buttonNumberEight.setOnClickListener {
            clickVibration(this)
            touchValue += Number.EIGHT.ordinal
            showCircleColored()
            validatePassword()
        }

        binding.buttonNumberNine.setOnClickListener {
            clickVibration(this)
            touchValue += Number.NINE.ordinal
            showCircleColored()
            validatePassword()
        }

    }

    private fun validatePassword() {
       val password = sharedPreferences.getValueString(SharedPreferences.PASSWORD)
       if (password.equals(touchValue)) {
           Timer().schedule(500){
               finish()
               startActivity(Intent(this@LoginActivity,MainActivity::class.java))
           }
       } else {
           if (countClicks==4) {
               Timer().schedule(500){
                   countClicks = 0
                   touchValue = ""
                   restartCircles()
                   wrongPasswordVibration(this@LoginActivity)
               }
           }
       }
    }

    private fun restartCircles() {
        binding.ivCircleOne.setImageResource(R.drawable.ic_circle_clean)
        binding.ivCircleTwo.setImageResource(R.drawable.ic_circle_clean)
        binding.ivCircleThree.setImageResource(R.drawable.ic_circle_clean)
        binding.ivCircleFour.setImageResource(R.drawable.ic_circle_clean)

    }

    private fun showCircleColored() {
        countClicks++

        when(countClicks) {
            Number.ONE.ordinal -> {
                binding.ivCircleOne.setImageResource(R.drawable.ic_circle_colored)
            }
            Number.TWO.ordinal -> {
                binding.ivCircleTwo.setImageResource(R.drawable.ic_circle_colored)
            }
            Number.THREE.ordinal -> {
                binding.ivCircleThree.setImageResource(R.drawable.ic_circle_colored)
            }
            Number.FOUR.ordinal -> {
                binding.ivCircleFour.setImageResource(R.drawable.ic_circle_colored)
            } else -> {
                if (countClicks>4) {
                    countClicks = 0
                }
            }
        }

    }
}