package com.roshaan.githubapp.data.datasource

import com.roshaan.githubapp.data.db.entity.RepositoryEntity


interface GithubLocalDataSource {
    suspend fun saveRepositories(list: List<RepositoryEntity>)
    suspend fun getRepositories(): List<RepositoryEntity>
    suspend fun deleteAllRecords()
}