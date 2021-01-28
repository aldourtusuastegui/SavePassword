package com.acsoft.savepassword.ui.accounts


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.get
import com.acsoft.savepassword.R
import com.acsoft.savepassword.application.AppConstants
import com.acsoft.savepassword.data.local.AppDatabase
import com.acsoft.savepassword.data.local.LocalAccountDataSource
import com.acsoft.savepassword.data.model.Account
import com.acsoft.savepassword.databinding.ActivityDetailBinding
import com.acsoft.savepassword.presentation.AccountViewModel
import com.acsoft.savepassword.presentation.AccountViewModelFactory
import com.acsoft.savepassword.repository.AccountRepositoryImpl
import com.acsoft.utils.copyToClipboard

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val viewModel by viewModels<AccountViewModel> {
        AccountViewModelFactory(
                AccountRepositoryImpl(
                        LocalAccountDataSource(
                                AppDatabase.getDatabase(
                                        this
                                ).AccountDao()
                        )
                )
        )
    }

    private var account: Account? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        init()
        copyToClipboard()

    }

    private fun init() {
        account = intent.getParcelableExtra(AppConstants.ACCOUNT)
        isFavorite = account!!.favorite

        binding.tvAccount.text = account!!.account
        binding.tvEmail.text = account!!.email
        binding.tvPassword.text = account!!.password
        binding.tvDate.text = account!!.date

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

    private fun copyToClipboard() {

        binding.llAccount.setOnClickListener {
            val account = binding.tvAccount.text.toString()
            copyToClipboard(it,this,account)
        }

        binding.llEmail.setOnClickListener {
            val email = binding.tvEmail.text.toString()
            copyToClipboard(it,this,email)
        }

        binding.llPassword.setOnClickListener {
            val password = binding.tvPassword.text.toString()
            copyToClipboard(it,this,password)
        }

        binding.llWebsite.setOnClickListener {
            val website = binding.tvWebsite.text.toString()
            copyToClipboard(it,this,website)
        }

        binding.llNotes.setOnClickListener {
            val notes = binding.tvNotes.text.toString()
            copyToClipboard(it,this,notes)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)

        if (isFavorite) {
            menu?.get(0)?.setIcon(R.drawable.ic_favorite_enabled)
        } else {
            menu?.get(0)?.setIcon(R.drawable.ic_favorite_disabled)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.item_favorite -> {
                isFavorite = if (isFavorite) {
                    item.setIcon(R.drawable.ic_favorite_disabled)
                    viewModel.setFavorite(account!!.id,false)
                    false
                } else {
                    item.setIcon(R.drawable.ic_favorite_enabled)
                    viewModel.setFavorite(account!!.id,true)
                    true
                }
                true
            }
            R.id.item_share -> {
                true
            }
            R.id.item_edit -> {
                val intent = Intent(this,InsertAccountActivity::class.java)
                intent.putExtra("SCREEN_NAME","Editar Cuenta")
                intent.putExtra(AppConstants.ACTION,AppConstants.UPDATE)
                intent.putExtra(AppConstants.ACCOUNT,account)
                startActivity(intent)
                true
            }
            R.id.item_delete -> {
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