package com.roshaan.githubapp.di.module

import android.content.Context
import androidx.room.Room
import com.roshaan.githubapp.R
import com.roshaan.githubapp.data.db.AppDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @JvmStatic
    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            AppDb::class.java,
            context.getString(R.string.db_name)
        ).build()

    @JvmStatic
    @Provides
    @Singleton
    fun providesRepositoryDao(appDb: AppDb) =
        appDb.repositoryDao()
}