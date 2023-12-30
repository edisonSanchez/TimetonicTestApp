package com.edisonsanchez.timetonictestapp.domain

import android.content.Context
import com.edisonsanchez.timetonictestapp.data.LoginRepository
import javax.inject.Inject

class UseCaseRegistrarApp @Inject constructor(private val loginRepository: LoginRepository) {

    fun invoke(context: Context) {
        loginRepository.registrarApp(context)
    }

}