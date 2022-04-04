package com.base.common_android.domain.repository

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SystemResourcesRepository @Inject constructor(
    @ApplicationContext private val context: Context
) : ISystemResourcesRepository {
    override fun getColor(resId: Int): Int {
        return context.getColor(resId)
    }

    override fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }

    override fun getString(@StringRes resId: Int, vararg formatArgs: Any?): String {
        return context.getString(resId, *formatArgs)
    }

    override fun getDrawable(resId: Int): Drawable? {
        if (resId == ISystemResourcesRepository.INVALID_RES) {
            return null
        }
        return AppCompatResources.getDrawable(context, resId)
    }

    override fun getDrawable(@DrawableRes resId: Int, @ColorInt colorFilter: Int): Drawable? {
        return getDrawable(resId)?.apply {
            this.colorFilter = PorterDuffColorFilter(colorFilter, PorterDuff.Mode.SRC_IN)
        }
    }
}
