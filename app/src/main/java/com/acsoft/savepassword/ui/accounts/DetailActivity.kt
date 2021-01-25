package com.acsoft.savepassword.ui.accounts

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
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
        copyEmail()


    }

    private fun init() {
        account = intent.getParcelableExtra("account")
        isFavorite = account!!.favorite

        binding.tvAccount.text = account!!.account
        binding.tvName.text = account!!.email
        binding.tvPassword.text = account!!.password

        if (account!!.website.isEmpty()) {
            binding.llWebsite.visibility = View.GONE
        } else {
            binding.llWebsite.visibility = View.VISIBLE
            binding.tvWebsite.text = account!!.website
        }

        if (account!!.notes.isEmpty()) {
            binding.llNotes.visibility = View.GONE
        } else {
            binding.llNotes.visibility = View.VISIBLE
            binding.tvNotes.text = account!!.notes
        }

    }

    private fun copyEmail() {

        binding.llEmail.setOnClickListener {
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("text", binding.tvAccount.text.toString())
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_LONG).show()
        }
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