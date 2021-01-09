package com.roshaan.githubapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.roshaan.githubapp.data.db.dao.RepositoryDao
import com.roshaan.githubapp.data.db.entity.RepositoryEntity


@Database(entities = [RepositoryEntity::class], version = 1)
abstract class AppDb : RoomDatabase() {

    abstract fun repositoryDao(): RepositoryDao

}