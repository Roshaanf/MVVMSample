package com.roshaan.githubapp.data.repository

import com.roshaan.githubapp.data.model.Repository


interface GithubRepository {
    suspend fun getRepositories(isHardRefresh: Boolean): List<Repository>?
}