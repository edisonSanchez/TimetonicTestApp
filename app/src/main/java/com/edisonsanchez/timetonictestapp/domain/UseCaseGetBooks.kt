package com.edisonsanchez.timetonictestapp.domain

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.edisonsanchez.timetonictestapp.data.BooksRepository
import com.edisonsanchez.timetonictestapp.data.ResponseGetAllBooks
import javax.inject.Inject

class UseCaseGetBooks @Inject constructor(private val booksRepository: BooksRepository) {

    val mutableLiveDataBooks : MutableLiveData<ResponseGetAllBooks?> =
        booksRepository.mutableLiveDataBooks

    fun invoke(context: Context) {
        booksRepository.getBooks(context)
    }

}