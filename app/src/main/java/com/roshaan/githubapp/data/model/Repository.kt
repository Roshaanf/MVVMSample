package com.roshaan.githubapp.data.model

data class Repository(
        val ownerName: String,
        val fullName: String,
        val description: String,
        val avatar: String,
        val starsCount: Long
)