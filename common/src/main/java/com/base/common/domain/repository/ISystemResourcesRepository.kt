package com.base.common.domain.repository

interface ISystemResourcesRepository {
    fun getString(resId: Int): String
}