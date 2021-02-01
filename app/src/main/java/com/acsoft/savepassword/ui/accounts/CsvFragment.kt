package com.acsoft.savepassword.ui.accounts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acsoft.savepassword.R
import com.acsoft.savepassword.databinding.FragmentCsvBinding

class CsvFragment : Fragment() {

    private lateinit var binding: FragmentCsvBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_csv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCsvBinding.bind(view)


        binding.btnExportCsv.setOnClickListener {

        }
    }


    private fun exportCsv() {

    }

}