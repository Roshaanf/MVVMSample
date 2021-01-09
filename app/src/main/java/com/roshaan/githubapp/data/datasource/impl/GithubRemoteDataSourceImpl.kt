package com.avanza.sadapayrough.data.datasource.impl

import com.avanza.sadapayrough.data.GithubService
import com.avanza.sadapayrough.data.datasource.GithubRemoteDataSource
import com.avanza.sadapayrough.data.dto.RepositoryListDTO
import com.avanza.sadapayrough.di.qualifier.DefaultDispatchcer
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