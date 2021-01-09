package com.roshaan.githubapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.roshaan.githubapp.data.db.entity.RepositoryEntity

@Dao
interface RepositoryDao {

    @Query("SELECT * FROM repositoryentity")
    suspend fun getRepositories(): List<RepositoryEntity>

    @Insert
    suspend fun saveRepositories(list: List<RepositoryEntity>)

    @Query("DELETE FROM repositoryentity")
    suspend fun deleteAllRecords()
}