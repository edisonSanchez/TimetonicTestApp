package com.edisonsanchez.timetonictestapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.edisonsanchez.timetonictestapp.R
import com.edisonsanchez.timetonictestapp.data.CustomSharedPreferences
import com.edisonsanchez.timetonictestapp.data.ResponseOkCreateSessionKey
import com.edisonsanchez.timetonictestapp.data.ResponseOkLogin
import com.edisonsanchez.timetonictestapp.data.hideKeyBoard
import com.edisonsanchez.timetonictestapp.data.showSimpleAlertDialog
import com.edisonsanchez.timetonictestapp.ui.viewModel.LoginActivityViewModel
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel : LoginActivityViewModel by viewModels()
    private lateinit var email: TextInputEditText
    private lateinit var password : TextInputEditText
    private lateinit var botonSignIn : Button
    private lateinit var backgroundProgress : View
    private lateinit var progressBar: ProgressBar
    private lateinit var preferences: CustomSharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        botonSignIn = findViewById(R.id.boton_login)
        backgroundProgress = findViewById(R.id.background_progress)
        progressBar = findViewById(R.id.progress_bar)
        viewModel.registrarApp(this)
        preferences = CustomSharedPreferences(this)
        configObservers()
        botonSignIn.setOnClickListener {
            hideKeyBoard(this, it)
            showProgress()
            viewModel.login(email.text.toString(), password.text.toString(), this)
        }
    }

    private fun showProgress() {
        backgroundProgress.visibility = View.VISIBLE
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        backgroundProgress.visibility = View.GONE
        progressBar.visibility = View.GONE
    }

    private fun configObservers() {
        val observerLogin = Observer<ResponseOkLogin?> {
            it?.let {
                if (it.status != "nok") {
                    preferences.oauthKey = it.oauthkey
                    preferences.oauthUser = it.oauthUser
                    preferences.userId = it.id
                    viewModel.getSessionKey(this)
                } else {
                    hideProgress()
                    showSimpleAlertDialog(this, getString(R.string.text_fail_authentication))
                }
            } ?: run {
                hideProgress()
                showSimpleAlertDialog(this, getString(R.string.text_error_login))
            }
        }
        val observerEmail = Observer<Boolean> {
            if (!it) {
                hideProgress()
                showSimpleAlertDialog(this, getString(R.string.text_error_email))
            }
        }
        val observerPassword = Observer<Boolean> {
            if (!it) {
                hideProgress()
                showSimpleAlertDialog(this, getString(R.string.text_error_password))
            }

        }
        val observerSessionKey = Observer<ResponseOkCreateSessionKey?> {
            it?.let {
                if (it.status != "nok") {
                    hideProgress()
                    preferences.sessionKey = it.sesskey
                    startActivity(Intent(this, BooksActivity::class.java))
                    finish()
                } else {
                    hideProgress()
                    showSimpleAlertDialog(this, getString(R.string.text_error_token))
                }
            } ?: run {
                hideProgress()
                showSimpleAlertDialog(this, getString(R.string.text_error_token))
            }
        }
        viewModel.sucessfullGetSessionKey.observe(this, observerSessionKey)
        viewModel.sucessfullLogin.observe(this, observerLogin)
        viewModel.sucessfullEmail.observe(this, observerEmail)
        viewModel.sucessfullPassword.observe(this, observerPassword)
    }
}