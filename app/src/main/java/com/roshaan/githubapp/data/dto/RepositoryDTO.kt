package com.roshaan.githubapp.data.dto

data class RepositoryDTO(
    val id: Int,
    val full_name: String,
    val owner: Owner,
    val description: String,
    val stargazers_count: Long
) {
}

data class Owner(
    val login: String,
    val avatar_url: String
)