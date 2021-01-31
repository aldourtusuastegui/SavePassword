package com.acsoft.savepassword.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.acsoft.savepassword.R
import com.acsoft.savepassword.databinding.ConfirmDialogFragmentBinding

class ConfirmDialogFragment(private var confirmDialogAction: ConfirmDialogAction) : DialogFragment() {

    private lateinit var binding: ConfirmDialogFragmentBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.confirm_dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ConfirmDialogFragmentBinding.bind(view)

        binding.tvCancel.setOnClickListener {
            confirmDialogAction.cancelOnClick(dialog!!)
        }

    }

    interface ConfirmDialogAction {
        fun cancelOnClick(dialog: Dialog)
        fun confirmOnClick()
    }
}