package com.roshaan.githubapp.data

import com.roshaan.githubapp.data.dto.RepositoryListDTO
import retrofit2.http.GET

interface GithubService {

    @GET("search/repositories?q=language=+sort:stars")
    suspend fun getRepositories(): RepositoryListDTO
}