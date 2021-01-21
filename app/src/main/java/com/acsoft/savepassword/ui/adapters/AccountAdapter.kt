package com.acsoft.savepassword.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.acsoft.savepassword.core.BaseViewHolder
import com.acsoft.savepassword.data.model.Account
import com.acsoft.savepassword.databinding.AccountItemBinding

class AccountAdapter: RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var accountList = listOf<Account>()

    fun setAccountList(accountList: List<Account>) {
        this.accountList = accountList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
                AccountItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AccountViewHolder(itemBinding, parent.context)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder) {
            is AccountViewHolder -> holder.bind(accountList[position])
        }
    }

    override fun getItemCount(): Int {
        return accountList.size
    }

    private inner class AccountViewHolder(val binding : AccountItemBinding, val context: Context) :
            BaseViewHolder<Account>(binding.root) {
        override fun bind(item: Account) {
            binding.tvTitle.text = item.title
            binding.tvUsername.text = item.name
        }

    }

}

