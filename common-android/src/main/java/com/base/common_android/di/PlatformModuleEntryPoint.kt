package com.base.common_android.di

import com.base.common.domain.repository.ISystemResourcesRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface PlatformModuleEntryPoint {
    fun provideSystemResourcesRepository(): ISystemResourcesRepository
}