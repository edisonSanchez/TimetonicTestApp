package com.edisonsanchez.timetonictestapp.ui.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edisonsanchez.timetonictestapp.data.ResponseOkCreateSessionKey
import com.edisonsanchez.timetonictestapp.data.ResponseOkLogin
import com.edisonsanchez.timetonictestapp.domain.UseCaseGetSessionKey
import com.edisonsanchez.timetonictestapp.domain.UseCaseLogin
import com.edisonsanchez.timetonictestapp.domain.UseCaseRegistrarApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginActivityViewModel @Inject constructor(private val useCaseRegistrarApp: UseCaseRegistrarApp,
                                                 private val useCaseLogin: UseCaseLogin,
                                                 private val useCaseGetSessionKey: UseCaseGetSessionKey)
    : ViewModel() {

    private var _sucessfullLogin = useCaseLogin.responseLogin
    val sucessfullLogin: LiveData<ResponseOkLogin?> = _sucessfullLogin
    private var _sucessfullEmail = MutableLiveData<Boolean>()
    val sucessfullEmail: LiveData<Boolean> = _sucessfullEmail
    private var _sucessfullPassword = MutableLiveData<Boolean>()
    val sucessfullPassword: LiveData<Boolean> = _sucessfullPassword
    private var _sucessfullGetSessionKey = useCaseGetSessionKey.getSessionKeyResponse
    val sucessfullGetSessionKey: LiveData<ResponseOkCreateSessionKey?> = _sucessfullGetSessionKey

    fun registrarApp(context: Context) {
        useCaseRegistrarApp.invoke(context)
    }

    fun login(email: String, password: String, context: Context) {
        if (email.isNotEmpty() && email.contains("@") && email.contains(".com")) {
            if (password.isNotEmpty()) {
               useCaseLogin.invoke(email, password, context)
            } else {
                _sucessfullPassword.value = false
            }
        } else {
            _sucessfullEmail.value = false
        }
    }

    fun getSessionKey(context: Context) {
        useCaseGetSessionKey.invoke(context)
    }

}