package com.roshaan.githubapp.data.repository.impl

import com.roshaan.githubapp.data.datasource.GithubLocalDataSource
import com.roshaan.githubapp.data.datasource.GithubRemoteDataSource
import com.roshaan.githubapp.data.mapper.DataMapper
import com.roshaan.githubapp.data.model.Repository
import com.roshaan.githubapp.data.repository.GithubRepository
import com.roshaan.githubapp.data.util.Clock
import com.roshaan.githubapp.data.util.DATA_EXPIRATION_TIME_IN_MNTS
import com.roshaan.githubapp.di.qualifier.DefaultDispatchcer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GithubRepositoryImpl @Inject constructor(
        @DefaultDispatchcer private val dispatcher: CoroutineDispatcher,
        private val githubRemoteDataSource: GithubRemoteDataSource,
        private val githubLocalDataSource: GithubLocalDataSource,
        private val dataMapper: DataMapper,
        private val clock: Clock
) :
        GithubRepository {

    override suspend fun getRepositories(isHardRefresh: Boolean): List<Repository>? =
        withContext(dispatcher) {

//                fetching cached data
            val repositories = githubLocalDataSource.getRepositories()

//            if it is hardRefresh then fetch data from remote directly
            if (isHardRefresh)
                fetchRepositoriesFromRemoteAndUpdateCache()
            else {
                /*
                * if data not found locally then fetch fresh data from remote
                * if data is found locally but more than 5 mnts older then fetch fresh data from remote
                * else return stored data
                * */


//               if data not found locally  OR
//               if data is found locally and 5 minutes has passed since its stored
                if (repositories.size == 0 || (repositories.size > 0 &&
                            clock.hasTimePassed(
                                DATA_EXPIRATION_TIME_IN_MNTS, repositories.get(0).createdAt
                            ))
                )
                    fetchRepositoriesFromRemoteAndUpdateCache()
                else
//                    returning stored data
                    dataMapper.transformEntityListToRepositoryList(repositories)

            }

        }


    private suspend fun fetchRepositoriesFromRemoteAndUpdateCache(): List<Repository>? {
        val remoteRepositories = githubRemoteDataSource.getRepositories()

//        if there is error in fetching from remote thn delete cached records
        if (remoteRepositories == null) {
            githubLocalDataSource.deleteAllRecords()
            return null
        } else {
//            api return success

//            deleting all records
            githubLocalDataSource.deleteAllRecords()

            val mappedData =
                dataMapper.transformRepositoryListDTOToRepositoryEntityList(remoteRepositories)
//            inserting new data
            githubLocalDataSource.saveRepositories(mappedData)

            return dataMapper.transformEntityListToRepositoryList(mappedData)

        }

    }


}