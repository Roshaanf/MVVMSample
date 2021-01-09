package com.roshaan.githubapp.data.datasource.impl


import com.roshaan.githubapp.data.datasource.GithubLocalDataSource
import com.roshaan.githubapp.data.db.dao.RepositoryDao
import com.roshaan.githubapp.data.db.entity.RepositoryEntity
import com.roshaan.githubapp.data.util.Clock
import com.roshaan.githubapp.di.qualifier.DefaultDispatchcer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubLocalDataSourceImpl @Inject constructor(
    @DefaultDispatchcer private val dispatcher: CoroutineDispatcher,
    private val repositoryDao: RepositoryDao,
    private val clock: Clock
) :
    GithubLocalDataSource {

    override suspend fun saveRepositories(list: List<RepositoryEntity>) =
        withContext(dispatcher) {
            val currentTime = clock.getCurrentTime()
//            appending created time before saving data
            list.forEach { item -> item.createdAt = currentTime }

            repositoryDao.saveRepositories(list)
        }

    override suspend fun getRepositories(): List<RepositoryEntity> =
        withContext(dispatcher) {
            repositoryDao.getRepositories()
        }

    override suspend fun deleteAllRecords() {
        withContext(dispatcher) {
            repositoryDao.deleteAllRecords()
        }
    }
}