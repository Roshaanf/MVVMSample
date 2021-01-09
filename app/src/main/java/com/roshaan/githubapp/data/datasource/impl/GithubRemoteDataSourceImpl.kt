package com.roshaan.githubapp.data.datasource.impl


import com.roshaan.githubapp.data.GithubService
import com.roshaan.githubapp.data.datasource.GithubRemoteDataSource
import com.roshaan.githubapp.data.dto.RepositoryListDTO
import com.roshaan.githubapp.di.qualifier.DefaultDispatchcer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRemoteDataSourceImpl @Inject constructor(
    @DefaultDispatchcer private val dispatcher: CoroutineDispatcher,
    private val githubService: GithubService
) :
    GithubRemoteDataSource {
    override suspend fun getRepositories(): RepositoryListDTO? =
        withContext(dispatcher) {
            try {
                githubService.getRepositories()
            } catch (e: Exception) {
//            return null if their is any exception
                null
            }
        }

}