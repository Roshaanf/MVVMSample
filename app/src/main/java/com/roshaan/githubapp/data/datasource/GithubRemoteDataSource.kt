package com.roshaan.githubapp.data.datasource

import com.roshaan.githubapp.data.dto.RepositoryListDTO


interface GithubRemoteDataSource {
    suspend fun getRepositories(): RepositoryListDTO?
}