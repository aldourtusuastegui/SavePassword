package com.acsoft.savepassword.ui.accounts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.acsoft.savepassword.R
import com.acsoft.savepassword.data.model.Account
import com.acsoft.savepassword.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private var account: Account? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        init()

    }

    private fun init() {
        account = intent.getParcelableExtra("account")
        isFavorite = account!!.favorite

        binding.tvAccount.text = account!!.account
        binding.tvName.text = account!!.email
        binding.tvPassword.text = account!!.password
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.item_favorite -> {
                isFavorite = if (isFavorite) {
                    item.setIcon(R.drawable.ic_favorite_disabled)
                    false
                } else {
                    item.setIcon(R.drawable.ic_favorite_enabled)
                    true
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}