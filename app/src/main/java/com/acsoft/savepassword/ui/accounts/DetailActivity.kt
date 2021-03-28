package com.acsoft.savepassword.ui.accounts


import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.get
import com.acsoft.savepassword.R
import com.acsoft.savepassword.application.AppConstants
import com.acsoft.savepassword.data.model.Account
import com.acsoft.savepassword.databinding.ActivityDetailBinding
import com.acsoft.savepassword.presentation.AccountViewModel
import com.acsoft.savepassword.utils.copyToClipboard
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val requestCode = 0

    private val viewModel by viewModels<AccountViewModel>()

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
        updateData(account!!)
    }

    private fun updateData(account: Account) {

        isFavorite = account.favorite

        binding.tvAccount.text = account.account
        binding.tvEmail.text = account.email
        binding.tvPassword.text = account.password
        binding.tvDate.text = account.date

        if (account.website.isEmpty()) {
            binding.llWebsite.visibility = View.GONE
        } else {
            binding.llWebsite.visibility = View.VISIBLE
            binding.tvWebsite.text = account.website
        }

        if (account.notes.isEmpty()) {
            binding.llNotes.visibility = View.GONE
        } else {
            binding.llNotes.visibility = View.VISIBLE
            binding.tvNotes.text = account.notes
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

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT,"${getString(R.string.account)}: ${account?.account}\n" +
                            "${getString(R.string.email)}: ${account?.email}\n" +
                            "${getString(R.string.password)}: ${account?.password}\n" +
                            "${getString(R.string.website)}: ${account?.website}\n" +
                            "${getString(R.string.notes)}: ${account?.notes}")
                    type = "text/json"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)

                true
            }
            R.id.item_edit -> {
                val intent = Intent(this,InsertAccountActivity::class.java)
                intent.putExtra(AppConstants.ACTION,AppConstants.UPDATE)
                intent.putExtra(AppConstants.ACCOUNT,account)
                startActivityForResult(intent,requestCode)
                true
            }
            R.id.item_delete -> {
                deleteDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == this.requestCode) {
            if (resultCode == Activity.RESULT_OK) {
                account = data?.getParcelableExtra(AppConstants.ACCOUNT)
                updateData(account!!)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun deleteDialog() {
        MaterialAlertDialogBuilder(this)
                .setTitle(getString(R.string.question_delete))
                .setMessage("")
                .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton(getString(R.string.delete)) { dialog, _ ->
                    viewModel.deleteAccount(this.account!!)
                    dialog.cancel()
                    finish()
                }
                .show()
    }
}