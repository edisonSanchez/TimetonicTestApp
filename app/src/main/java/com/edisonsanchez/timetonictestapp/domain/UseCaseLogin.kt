package com.edisonsanchez.timetonictestapp.domain

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.edisonsanchez.timetonictestapp.data.LoginRepository
import com.edisonsanchez.timetonictestapp.data.ResponseOkLogin
import javax.inject.Inject

class UseCaseLogin @Inject constructor(private val loginRepository: LoginRepository) {

    val responseLogin: MutableLiveData<ResponseOkLogin?> = loginRepository.mutableLiveDataLogin

    fun invoke(email: String, password: String, context: Context) {
        loginRepository.login(email, password, context)
    }

}