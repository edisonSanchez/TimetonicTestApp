package com.edisonsanchez.timetonictestapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edisonsanchez.timetonictestapp.R
import com.edisonsanchez.timetonictestapp.data.Books
import com.edisonsanchez.timetonictestapp.data.BooksAdapter
import com.edisonsanchez.timetonictestapp.data.ResponseGetAllBooks
import com.edisonsanchez.timetonictestapp.data.showSimpleAlertDialog
import com.edisonsanchez.timetonictestapp.ui.viewModel.BooksActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BooksActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var backgroundProgress: View
    private lateinit var progressBar: ProgressBar
    private val viewModel: BooksActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)
        recyclerView = findViewById(R.id.recycler_view)
        backgroundProgress = findViewById(R.id.background_progress)
        progressBar = findViewById(R.id.progress_bar)
        viewModel.getBooks(this)
        configObservers()
    }

    private fun configObservers() {
        val observer = Observer<ResponseGetAllBooks?> {
            it?.let {
                configRecyclerView(it.allBooks)
                hideProgress()
            } ?: run {
                showSimpleAlertDialog(this, getString(R.string.text_error_get_books))
                hideProgress()
            }
        }
        viewModel.getAllBooks.observe(this, observer)
    }

    private fun configRecyclerView(allBooks: Books) {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
            false)
        recyclerView.layoutManager =  layoutManager
        val adapter = BooksAdapter(allBooks, this)
        recyclerView.adapter = adapter
    }

    private fun hideProgress() {
        backgroundProgress.visibility = View.GONE
        progressBar.visibility = View.GONE
    }
}