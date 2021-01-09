package com.roshaan.githubapp.di.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
internal object GsonModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideGson() = Gson()
}