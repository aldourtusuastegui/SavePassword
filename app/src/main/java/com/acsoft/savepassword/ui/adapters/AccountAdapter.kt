package com.acsoft.savepassword.ui.adapters

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.acsoft.savepassword.R
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
            binding.tvTitle.text = item.title
            binding.tvUsername.text = item.name
            binding.tvDate.text = item.date
            binding.tvMark.text = item.title.first().toString().capitalize()
            Log.d("NEW",adapterPosition.toString())
            circleColor(binding,context,adapterPosition)
        }
    }

    private fun circleColor(binding : AccountItemBinding,context: Context,position: Int) {

        var color :Int = position
        if (position>=5) {
            color = 0
        }

        when (color) {
            0 -> {
                binding.tvMark.backgroundTintList = ContextCompat.getColorStateList(context, R.color.color_one)
                color.inc()
            }

            1 -> {
                binding.tvMark.backgroundTintList = ContextCompat.getColorStateList(context, R.color.color_two)
                color.inc()
            }

            2 -> {
                binding.tvMark.backgroundTintList = ContextCompat.getColorStateList(context, R.color.color_three)
                color.inc()
            }

            3 -> {
                binding.tvMark.backgroundTintList = ContextCompat.getColorStateList(context, R.color.color_four)
                color.inc()
            }

            4 -> {
                binding.tvMark.backgroundTintList = ContextCompat.getColorStateList(context, R.color.color_five)
                color.inc()
            }
        }

    }

}

