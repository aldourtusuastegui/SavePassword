package com.acsoft.savepassword.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.acsoft.savepassword.R
import com.acsoft.savepassword.core.BaseViewHolder
import com.acsoft.savepassword.data.model.Account
import com.acsoft.savepassword.databinding.AccountItemBinding

class AccountAdapter(private val itemClickListener: OnAccountClickListener): RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var accountList = listOf<Account>()

    fun setAccountList(accountList: List<Account>) {
        this.accountList = accountList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
                AccountItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val holder = AccountViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                    ?: return@setOnClickListener
            itemClickListener.onAccountClick(accountList[position])
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder) {
            is AccountViewHolder -> {
                holder.bind(accountList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return accountList.size
    }

    private inner class AccountViewHolder(val binding : AccountItemBinding, val context: Context) :
            BaseViewHolder<Account>(binding.root) {
        override fun bind(item: Account) {
            binding.tvTitle.text = item.account
            binding.tvUsername.text = item.email
            binding.tvDate.text = item.date
            binding.tvMark.text = item.account.first().toString().capitalize()
            circleColor(binding,context,adapterPosition)
        }
    }

    private fun circleColor(binding : AccountItemBinding,context: Context,position: Int) {

        when (position%4) {
            0 -> {
                binding.tvMark.backgroundTintList = ContextCompat.getColorStateList(context, R.color.color_one)
            }

            1 -> {
                binding.tvMark.backgroundTintList = ContextCompat.getColorStateList(context, R.color.color_two)
            }

            2 -> {
                binding.tvMark.backgroundTintList = ContextCompat.getColorStateList(context, R.color.color_three)
            }

            3 -> {
                binding.tvMark.backgroundTintList = ContextCompat.getColorStateList(context, R.color.color_four)
            }
        }

    }

    interface OnAccountClickListener {
        fun onAccountClick(account: Account)
    }

}

