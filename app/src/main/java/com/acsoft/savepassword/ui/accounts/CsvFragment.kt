package com.acsoft.savepassword.ui.accounts

import android.accounts.Account
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.acsoft.savepassword.R
import com.acsoft.savepassword.data.local.AppDatabase
import com.acsoft.savepassword.data.local.LocalAccountDataSource
import com.acsoft.savepassword.databinding.FragmentCsvBinding
import com.acsoft.savepassword.presentation.AccountViewModel
import com.acsoft.savepassword.presentation.AccountViewModelFactory
import com.acsoft.savepassword.repository.AccountRepositoryImpl
import com.acsoft.utils.generateFile
import com.acsoft.utils.goToFileIntent
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import java.io.File


class CsvFragment : Fragment() {

    private lateinit var binding: FragmentCsvBinding

    private val viewModel by viewModels<AccountViewModel> {
        AccountViewModelFactory(AccountRepositoryImpl(LocalAccountDataSource(AppDatabase.getDatabase(requireContext()).AccountDao())))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_csv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCsvBinding.bind(view)


        binding.btnExportCsv.setOnClickListener {
            exportDatabaseToCSVFile()
        }
    }


    companion object {
        private var MOVIES_SHOWN = true
    }

    private fun getCSVFileName() : String =
        if (MOVIES_SHOWN) "MoviesRoomExample.csv" else "DirectorsRoomExample.csv"

    private fun exportDatabaseToCSVFile() {
        val csvFile = generateFile(requireContext(), getCSVFileName())
        if (csvFile != null) {
            exportMoviesWithDirectorsToCSVFile(csvFile)
            Toast.makeText(requireContext(), "Generado", Toast.LENGTH_LONG).show()
            val intent = goToFileIntent(requireContext(), csvFile)
            startActivity(intent)
        } else {
            Toast.makeText(requireContext(),"No generado", Toast.LENGTH_LONG).show()
        }
    }


    private fun exportMoviesWithDirectorsToCSVFile(csvFile: File) {
        csvWriter().open(csvFile, append = false) {
            // Header
            writeRow(listOf("[id]", "[jola]", "[ejemplo]"))
            writeRow(listOf("[id]", "[jola]", "[ejemplo]"))
            writeRow(listOf("[id]", "[jola]", "[ejemplo]"))
            writeRow(listOf("[id]", "[jola]", "[ejemplo]"))
        }
    }

}