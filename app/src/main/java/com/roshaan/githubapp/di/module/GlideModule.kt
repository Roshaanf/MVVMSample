package com.roshaan.githubapp.di.module

import android.content.Context
import com.bumptech.glide.Glide
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
object GlideModule {

    @JvmStatic
    @Provides
    @com.roshaan.githubapp.di.qualifier.Glide
    fun provideGlide(@ApplicationContext context: Context) = Glide.with(context)
}