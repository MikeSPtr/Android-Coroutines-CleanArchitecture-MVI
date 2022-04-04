package com.base.common_android.data.source

import android.content.SharedPreferences
import com.base.common.domain.repository.IPreferencesDataSource
import com.base.common.utils.getNotEmptyOrNull
import javax.inject.Inject

class PreferencesDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : IPreferencesDataSource {

    private val sharedPreferencesEditor = sharedPreferences.edit()

    override fun putString(key: String, value: String?): IPreferencesDataSource {
        with(sharedPreferencesEditor) {
            putString(key, value)
            apply()
        }
        return this
    }

    override fun getString(key: String, defValue: String?): String? {
        return sharedPreferences.getString(key, defValue)
    }

    override fun remove(key: String): IPreferencesDataSource {
        with(sharedPreferencesEditor) {
            remove(key)
            apply()
        }
        return this
    }

    override fun contains(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    override fun getNotEmptyStringOrNull(key: String): String? {
        return getString(key, null).getNotEmptyOrNull()
    }
}