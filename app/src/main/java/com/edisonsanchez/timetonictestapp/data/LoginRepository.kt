package com.edisonsanchez.timetonictestapp.data

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.edisonsanchez.timetonictestapp.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class LoginRepository @Inject constructor(private val endPointsApi: EndPointsApi) {

    private lateinit var sharedPreferences: CustomSharedPreferences
    val mutableLiveDataLogin = MutableLiveData<ResponseOkLogin?>()
    val mutableLiveDataSessionKey = MutableLiveData<ResponseOkCreateSessionKey?>()

    fun registrarApp(context: Context) {
        sharedPreferences = CustomSharedPreferences(context)
        endPointsApi.registrarAplicacion(VERSION_API, REQ_REGISTER_APP,
            context.getString(R.string.app_name)).enqueue(
            object : Callback<ResponseOkCreateAppKey> {
            override fun onResponse(call: Call<ResponseOkCreateAppKey>,
                response: Response<ResponseOkCreateAppKey>
            ) {
                response.body()?.let {
                   sharedPreferences.appKey = it.appkey
                } ?: run {
                    sharedPreferences.appKey = ""
                }
            }

            override fun onFailure(call: Call<ResponseOkCreateAppKey>, t: Throwable) {
                sharedPreferences.appKey = ""
            }

        })
    }

    fun login(email: String, password: String, context: Context) {
        sharedPreferences = CustomSharedPreferences(context)
        endPointsApi.login(VERSION_API, REQ_LOGIN_APP, email, password, sharedPreferences.appKey)
            .enqueue(object : Callback<ResponseOkLogin> {
                override fun onResponse(call: Call<ResponseOkLogin>,
                    response: Response<ResponseOkLogin>) {
                    response.body()?.let {
                        mutableLiveDataLogin.value = it
                    } ?: run {
                        mutableLiveDataLogin.value = null
                    }
                }

                override fun onFailure(call: Call<ResponseOkLogin>, t: Throwable) {
                    mutableLiveDataLogin.value = null
                }
            })
    }

    fun getSessionKey(context: Context)  {
        sharedPreferences = CustomSharedPreferences(context)
        endPointsApi.createSessionKey(VERSION_API, REQ_CREATE_SESSION_KEY,
            sharedPreferences.oauthUser,  sharedPreferences.oauthUser,
            sharedPreferences.oauthKey, "")
            .enqueue(object : Callback<ResponseOkCreateSessionKey> {
                override fun onResponse(call: Call<ResponseOkCreateSessionKey>,
                    response: Response<ResponseOkCreateSessionKey>
                ) {
                    response.body()?.let {
                        mutableLiveDataSessionKey.value = it
                    } ?: run {
                        mutableLiveDataSessionKey.value = null
                    }
                }

                override fun onFailure(call: Call<ResponseOkCreateSessionKey>, t: Throwable) {
                    mutableLiveDataSessionKey.value = null
                }
            })
    }

}