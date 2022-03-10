package com.base.common_android.di

import android.content.Context
import android.content.SharedPreferences
import com.base.common.IDispatchers
import com.base.common.domain.repository.IPreferencesDataSource
import com.base.common_android.domain.repository.PreferencesDataSource
import com.base.common_android.domain.repository.SystemResourcesRepository
import com.base.common.domain.repository.ISystemResourcesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AndroidPlatformModule {
    @Provides
    fun provideSystemResourcesRepository(
        systemResourcesRepository: SystemResourcesRepository
    ): ISystemResourcesRepository {
        return systemResourcesRepository
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("default", Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun providePreferencesDataSource(sharedPreferences: SharedPreferences): IPreferencesDataSource {
        return PreferencesDataSource(sharedPreferences)
    }

    @Singleton
    @Provides
    fun provideDispatchers(): IDispatchers {
        return object : IDispatchers {
            override val Main: CoroutineDispatcher
                get() = Dispatchers.Main
            override val Default: CoroutineDispatcher
                get() = Dispatchers.Default
            override val IO: CoroutineDispatcher
                get() = Dispatchers.IO
            override val Unconfined: CoroutineDispatcher
                get() = Dispatchers.Unconfined
        }
    }
}