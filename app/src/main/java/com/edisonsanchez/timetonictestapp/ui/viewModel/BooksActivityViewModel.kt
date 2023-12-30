package com.edisonsanchez.timetonictestapp.ui.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edisonsanchez.timetonictestapp.data.ResponseGetAllBooks
import com.edisonsanchez.timetonictestapp.domain.UseCaseGetBooks
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BooksActivityViewModel @Inject constructor(private val useCaseGetBooks: UseCaseGetBooks) :
    ViewModel() {

    private var _getAllBooks : MutableLiveData<ResponseGetAllBooks?> =
        useCaseGetBooks.mutableLiveDataBooks
    val getAllBooks : LiveData<ResponseGetAllBooks?> = _getAllBooks

    fun getBooks(context: Context) {
        useCaseGetBooks.invoke(context)
    }

}