package com.roshaan.githubapp.data.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.roshaan.githubapp.data.db.AppDb
import com.roshaan.githubapp.data.db.entity.RepositoryEntity
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class RepositoryDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repositoryDao: RepositoryDao

    @Before
    fun setup() {
        var db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDb::class.java
        ).allowMainThreadQueries()
            .build()

        repositoryDao = db.repositoryDao()
    }

    @Test
    fun testSaveAndGetRepositories() {
        runBlockingTest {

            val repositoryList = getRepositoriesEntityList()
            repositoryDao.saveRepositories(repositoryList)

            Assert.assertEquals(repositoryList, repositoryDao.getRepositories())
        }
    }

   @Test
    fun testDeletRepositories() {
        runBlockingTest {

            repositoryDao.saveRepositories(getRepositoriesEntityList())
            repositoryDao.deleteAllRecords()

            Assert.assertEquals(listOf<RepositoryEntity>(), repositoryDao.getRepositories())
        }
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
        return listOf(repositoryEntityItem1)
    }

}