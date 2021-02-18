package com.acsoft.savepassword.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.acsoft.savepassword.R
import com.acsoft.savepassword.ui.login.LoginActivity
import com.acsoft.savepassword.ui.login.PasswordActivity
import com.acsoft.savepassword.utils.SharedPreferences
import kotlinx.coroutines.*

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        sharedPreferences = SharedPreferences(this)

        hideNavigationBar()

        lifecycleScope.launch {
            openNextActivity()
        }

    }

    private fun hideNavigationBar() {
        window.decorView.apply {
            @Suppress("DEPRECATION")
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
    }

    private suspend fun openNextActivity() {
        withContext(Dispatchers.IO) {
                delay(1000)
                finish()
                val pwd = sharedPreferences.getValueString(SharedPreferences.PASSWORD)
                if(pwd.isNullOrEmpty()) {
                    startActivity(Intent(this@SplashScreenActivity,PasswordActivity::class.java))
                } else {
                    startActivity(Intent(this@SplashScreenActivity,LoginActivity::class.java))
                }
        }
    }

}