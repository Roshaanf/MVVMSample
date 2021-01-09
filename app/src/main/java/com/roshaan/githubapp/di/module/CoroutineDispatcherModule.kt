package com.roshaan.githubapp.di.module

import com.roshaan.githubapp.di.qualifier.DefaultDispatchcer
import com.roshaan.githubapp.di.qualifier.IODispatchcer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object CoroutineDispatcherModule {

    @Singleton
    @JvmStatic
    @Provides
    @IODispatchcer
    fun provideIODispatcher() = Dispatchers.IO

    @Singleton
    @JvmStatic
    @Provides
    @DefaultDispatchcer
    fun provideDefaultDispatcher() = Dispatchers.Default
}