package com.roshaan.githubapp.data.datasource.impl

import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.roshaan.githubapp.data.db.dao.RepositoryDao
import com.roshaan.githubapp.data.db.entity.RepositoryEntity
import com.roshaan.githubapp.data.util.Clock
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GithubLocalDataSourceImplTest {

    @Mock
    private lateinit var clock: Clock

    @Mock
    private lateinit var repositoryDao: RepositoryDao

    private lateinit var githubLocalDataSourceImpl: GithubLocalDataSourceImpl

    private val fakeTime: Long = 12345

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        githubLocalDataSourceImpl = GithubLocalDataSourceImpl(
            TestCoroutineDispatcher(),
            repositoryDao,
            clock
        )
    }

    @Test
    fun `saveRepositories, call dao saveRepositories by appending time stamp to each record`() {
        runBlockingTest {

//            stubbing getCurrentTime
            whenever(clock.getCurrentTime()).thenReturn(fakeTime)

            githubLocalDataSourceImpl.saveRepositories(getRepositoriesEntityListWithoutTime())

            verify(repositoryDao).saveRepositories(eq(getRepositoriesEntityList()))

        }
    }

    private fun getRepositoriesEntityList(): List<RepositoryEntity> {
        val repositoryEntityItem1 = RepositoryEntity(
            fakeTime,
            1,
            "Ali", "Ali Salman"
            , "This is dummy repository 1",
            "dummy url1", 12
        )
        val repositoryEntityItem2 = RepositoryEntity(
            fakeTime,
            2,
            "Salman", "Salman Ali"
            , "This is dummy repository 2",
            "dummy url2", 13
        )

        return listOf(repositoryEntityItem1, repositoryEntityItem2)
    }

    private fun getRepositoriesEntityListWithoutTime(): List<RepositoryEntity> {
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