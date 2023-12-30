package com.edisonsanchez.timetonictestapp.data

import android.content.Context
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class BooksRepository @Inject constructor(private val endPointsApi: EndPointsApi) {

    private lateinit var customSharedPreferences: CustomSharedPreferences
    val mutableLiveDataBooks: MutableLiveData<ResponseGetAllBooks?> = MutableLiveData()

    fun getBooks(context: Context) {
        customSharedPreferences = CustomSharedPreferences(context)
        endPointsApi.getAllBooks(VERSION_API, REQ_GET_ALL_BOOKS, customSharedPreferences.oauthUser,
            customSharedPreferences.oauthUser, customSharedPreferences.sessionKey).enqueue(
                object : Callback<ResponseGetAllBooks> {
                    override fun onResponse(call: Call<ResponseGetAllBooks>,
                        response: Response<ResponseGetAllBooks>) {
                        response.body()?.let {
                            mutableLiveDataBooks.value = it
                        } ?: run {
                            mutableLiveDataBooks.value = null
                        }
                    }

                    override fun onFailure(call: Call<ResponseGetAllBooks>, t: Throwable) {
                        mutableLiveDataBooks.value = null
                    }
                }
            )
    }

}