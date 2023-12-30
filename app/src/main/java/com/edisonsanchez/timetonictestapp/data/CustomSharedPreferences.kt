package com.edisonsanchez.timetonictestapp.data

import android.content.Context

class CustomSharedPreferences(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences(NAME_PREFERENCES,
        Context.MODE_PRIVATE)
    private val editSharedPreferences = sharedPreferences.edit()

    var appKey
        get() = sharedPreferences.getString(APP_KEY, "") ?: ""
        set(value) = editSharedPreferences.putString(APP_KEY, value).apply()

    var oauthKey
        get() = sharedPreferences.getString(OAUTH_KEY, "")  ?: ""
        set(value) = editSharedPreferences.putString(OAUTH_KEY, value).apply()

    var oauthUser
        get() = sharedPreferences.getString(OAUTH_USER, "") ?: ""
        set(value) = editSharedPreferences.putString(OAUTH_USER, value).apply()

    var sessionKey
        get() = sharedPreferences.getString(SESSION_KEY, "") ?: ""
        set(value) = editSharedPreferences.putString(SESSION_KEY, value).apply()

    var userId
        get() = sharedPreferences.getString(USER_ID, "") ?: ""
        set(value) = editSharedPreferences.putString(USER_ID, value).apply()


    companion object {
        const val APP_KEY = "AppKey"
        const val OAUTH_KEY = "OauthKey"
        const val OAUTH_USER = "OauthUser"
        const val SESSION_KEY = "SessionKey"
        const val USER_ID = "UserId"
    }
}