package com.roshaan.githubapp.di.module

import com.roshaan.githubapp.data.datasource.GithubLocalDataSource
import com.roshaan.githubapp.data.datasource.GithubRemoteDataSource
import com.roshaan.githubapp.data.datasource.impl.GithubLocalDataSourceImpl
import com.roshaan.githubapp.data.datasource.impl.GithubRemoteDataSourceImpl
import com.roshaan.githubapp.data.repository.GithubRepository
import com.roshaan.githubapp.data.repository.impl.GithubRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
interface RepositoryModule {

    @Binds
    abstract fun bindGithubRepository(repository: GithubRepositoryImpl): GithubRepository

    @Binds
    abstract fun bindGithubLocalDataSource(dataSource: GithubLocalDataSourceImpl): GithubLocalDataSource

    @Binds
    abstract fun bindGithubRemoteDataSource(dataSource: GithubRemoteDataSourceImpl): GithubRemoteDataSource


}
