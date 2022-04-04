package com.base.common_android.domain.repository

import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface ISystemResourcesRepository {
    @ColorInt
    fun getColor(@ColorRes resId: Int): Int
    fun getString(@StringRes resId: Int): String
    fun getString(@StringRes resId: Int, vararg formatArgs: Any?): String
    fun getDrawable(@DrawableRes resId: Int): Drawable?
    fun getDrawable(@DrawableRes resId: Int, @ColorInt colorFilter: Int): Drawable?

    companion object {
        const val INVALID_RES = 0
    }
}
