package com.roshaan.githubapp.data.repository.impl

import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.roshaan.githubapp.data.datasource.GithubLocalDataSource
import com.roshaan.githubapp.data.datasource.GithubRemoteDataSource
import com.roshaan.githubapp.data.db.entity.RepositoryEntity
import com.roshaan.githubapp.data.dto.Owner
import com.roshaan.githubapp.data.dto.RepositoryDTO
import com.roshaan.githubapp.data.dto.RepositoryListDTO
import com.roshaan.githubapp.data.mapper.DataMapper
import com.roshaan.githubapp.data.model.Repository
import com.roshaan.githubapp.data.util.Clock
import com.roshaan.githubapp.data.util.DATA_EXPIRATION_TIME_IN_MNTS
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GithubRepositoryImplTest {
    @Mock
    private lateinit var githubLocalDataSource: GithubLocalDataSource

    @Mock
    private lateinit var githubRemoteDataSource: GithubRemoteDataSource

    @Mock
    private lateinit var clock: Clock

    private lateinit var repositoryImpl: GithubRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        repositoryImpl = spy(
            GithubRepositoryImpl(
                TestCoroutineDispatcher(),
                githubRemoteDataSource,
                githubLocalDataSource,
                DataMapper(),
                clock
            )
        )
    }

    @Test
    fun `getRepositories hardRefresh true, returns remote repositories`() {
        runBlockingTest {
            stubGetRemoteRepositories()

            val repositories = repositoryImpl.getRepositories(true)

            assertEquals(
                repositories, getRepositoriesList()
            )
        }
    }

    @Test
    fun `getRepositories hardRefresh true, returns null if error in fetching from remote`() {
        runBlockingTest {
            whenever(githubRemoteDataSource.getRepositories()).thenReturn(null)

            val repositories = repositoryImpl.getRepositories(true)

            assertEquals(
                repositories, null
            )
        }
    }

    @Test
    fun `getRepositories hardRefresh true, calls delete all records on successfull fetch`() {
        runBlockingTest {
            stubGetRemoteRepositories()

            repositoryImpl.getRepositories(true)

            verify(githubLocalDataSource).deleteAllRecords()
        }
    }

    @Test
    fun `getRepositories hardRefresh true, calls save repositories on successfull fetch`() {
        runBlockingTest {
            stubGetRemoteRepositories()

            repositoryImpl.getRepositories(true)

            verify(githubLocalDataSource).saveRepositories(getRepositoriesEntityListWithoutTime())
        }
    }

    @Test
    fun `getRepositories hardRefresh false, returns remote repositories if local data not available`() {
        runBlockingTest {
            stubGetRemoteRepositories()
            stubGetLocalRepositoriesEmpty()

            val repositories = repositoryImpl.getRepositories(false)

            assertEquals(
                repositories, getRepositoriesList()
            )
        }
    }

    @Test
    fun `getRepositories hardRefresh false, returns local repositories if local data is available and 5 minutes has not passed`() {
        runBlockingTest {
            stubGetLocalRepositories()

            val repositories = repositoryImpl.getRepositories(false)

            assertEquals(
                repositories, getRepositoriesList()
            )
        }
    }

    @Test
    fun `getRepositories hardRefresh false, returns remote repositories if local data is available but 5 minutes has passed`() {
        runBlockingTest {

//       regionstart     stubbing getLocalRepositories with different data
            val currentTime = System.currentTimeMillis()

            val repositoryEntityList =
                listOf(
                    RepositoryEntity(
                        currentTime,
                        1,
                        "Owner1",
                        "Fullname1",
                        "decription1",
                        "avatart1",
                        1
                    )
                )
            listOf(
                RepositoryEntity(
                    currentTime,
                    2,
                    "Owner2",
                    "Fullname2",
                    "decription2",
                    "avatart2",
                    2
                )
            )

            whenever(githubLocalDataSource.getRepositories()).thenReturn(repositoryEntityList)
//            endregion

//            stubbing remote response
            stubGetRemoteRepositories()

//            stubbing time pass to advance time by 5 minutes
            whenever(
                clock.hasTimePassed(
                    DATA_EXPIRATION_TIME_IN_MNTS,
                    currentTime
                )
            ).thenReturn(true)

            val repositories = repositoryImpl.getRepositories(false)

            assertEquals(
                repositories, getRepositoriesList()
            )
        }
    }

    private suspend fun stubGetRemoteRepositories() {
        whenever(githubRemoteDataSource.getRepositories())
            .thenReturn(getRepositoryListDtoData())
            .thenReturn(null)

    }

    private suspend fun stubGetLocalRepositoriesEmpty() {
        whenever(githubLocalDataSource.getRepositories())
            .thenReturn(listOf())
            .thenReturn(null)

    }

    private suspend fun stubGetLocalRepositories() {
        whenever(githubLocalDataSource.getRepositories())
            .thenReturn(getRepositoriesEntityList())
            .thenReturn(null)
    }


    private fun getRepositoryListDtoData(): RepositoryListDTO {
        var repositoryDTO1 = RepositoryDTO(
            1,
            "Ali Salman",
            Owner("Ali", "dummy url1"),
            "This is dummy repository 1",
            12
        )

        var repositoryDTO2 = RepositoryDTO(
            2,
            "Salman Ali",
            Owner("Salman", "dummy url2"),
            "This is dummy repository 2",
            13
        )


        return RepositoryListDTO(
            listOf(repositoryDTO1, repositoryDTO2)
        )

    }


    private fun getRepositoriesList(): List<Repository> {
        val repositoryItem1 = Repository(
            "Ali", "Ali Salman"
            , "This is dummy repository 1",
            "dummy url1", 12
        )
        val repositoryItem2 = Repository(
            "Salman", "Salman Ali"
            , "This is dummy repository 2",
            "dummy url2", 13
        )

        return listOf(repositoryItem1, repositoryItem2)
    }

    private fun getRepositoriesEntityList(): List<RepositoryEntity> {
        val currentTime = System.currentTimeMillis()

        val repositoryEntityItem1 = RepositoryEntity(
            currentTime,
            1,
            "Ali", "Ali Salman"
            , "This is dummy repository 1",
            "dummy url1", 12
        )
        val repositoryEntityItem2 = RepositoryEntity(
            currentTime,
            2,
            "Salman", "Salman Ali"
            , "This is dummy repository 2",
            "dummy url2", 13
        )

        return listOf(repositoryEntityItem1, repositoryEntityItem2)
    }

    private fun getRepositoriesEntityListWithoutTime(): List<RepositoryEntity> {
        val currentTime = System.currentTimeMillis()

        val repositoryEntityItem1 = RepositoryEntity(
            id = 1,
            ownerName = "Ali", fullName = "Ali Salman"
            , description = "This is dummy repository 1",
            avatarUrl = "dummy url1", stars = 12
        )
        val repositoryEntityItem2 = RepositoryEntity(
            id = 2,
            ownerName = "Salman", fullName = "Salman Ali"
            , description = "This is dummy repository 2",
            avatarUrl = "dummy url2", stars = 13
        )

        return listOf(repositoryEntityItem1, repositoryEntityItem2)
    }
}