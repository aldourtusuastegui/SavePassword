package com.acsoft.savepassword.ui.accounts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.acsoft.savepassword.R
import com.acsoft.savepassword.databinding.ActivityDetailBinding
import com.acsoft.savepassword.databinding.FragmentFavoritesBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}