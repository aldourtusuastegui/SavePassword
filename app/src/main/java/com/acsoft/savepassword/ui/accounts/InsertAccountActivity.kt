package com.acsoft.savepassword.ui.accounts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.acsoft.savepassword.databinding.ActivityInsertAccountBinding

class InsertAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInsertAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityInsertAccountBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}