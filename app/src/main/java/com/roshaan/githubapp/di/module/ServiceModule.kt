package com.roshaan.githubapp.di.module

import com.roshaan.githubapp.data.GithubService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ServiceModule {

    @Singleton
    @JvmStatic
    @Provides
    fun provideGithubService(retrofit: Retrofit) = retrofit.create(GithubService::class.java)
}