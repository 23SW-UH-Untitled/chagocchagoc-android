package com.untitled.unboxing.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferenceUtil @Inject constructor(
    @ApplicationContext context: Context
) {

    companion object {
        const val IS_LOGIN = "IS_LOGIN"
    }

    private val prefs: SharedPreferences =
        context.getSharedPreferences("token", Context.MODE_PRIVATE)

    fun getString(key: String, defValue: String) = prefs.getString(key, defValue).toString()

    fun setString(key: String, str: String) {
        prefs.edit().putString(key, str).apply()
    }

    fun getIsLogin(): Boolean {
        return prefs.getBoolean(IS_LOGIN, false)
    }

    fun login() {
        prefs.edit().putBoolean(IS_LOGIN, true).apply()
    }

    fun logout() {
        prefs.edit().putBoolean(IS_LOGIN, false).apply()
    }
}
