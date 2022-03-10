package com.base.common_android.domain.repository

import android.content.Context
import androidx.annotation.StringRes
import com.base.common.domain.repository.ISystemResourcesRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SystemResourcesRepository @Inject constructor(
    @ApplicationContext private val context: Context
) : ISystemResourcesRepository {

    override fun getString(@StringRes resId: Int): String {
       return context.getString(resId)
    }
}