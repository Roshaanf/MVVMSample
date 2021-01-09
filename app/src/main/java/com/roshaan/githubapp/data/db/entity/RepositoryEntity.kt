package com.roshaan.githubapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RepositoryEntity(
    var createdAt: Long = 0,
    @PrimaryKey
    var id: Int,
    var ownerName: String,
    var fullName: String,
    var description: String,
    var avatarUrl: String,
    var stars: Long
)