package com.edisonsanchez.timetonictestapp.domain

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.edisonsanchez.timetonictestapp.data.LoginRepository
import com.edisonsanchez.timetonictestapp.data.ResponseOkCreateSessionKey
import javax.inject.Inject

class UseCaseGetSessionKey @Inject constructor(private val loginRepository: LoginRepository) {

    val getSessionKeyResponse: MutableLiveData<ResponseOkCreateSessionKey?>
    = loginRepository.mutableLiveDataSessionKey

    fun invoke(context: Context){
        loginRepository.getSessionKey(context)
    }

}