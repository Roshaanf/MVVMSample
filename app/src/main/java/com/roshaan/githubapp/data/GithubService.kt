package com.avanza.sadapayrough.data

import com.avanza.sadapayrough.data.dto.RepositoryDTO
import com.avanza.sadapayrough.data.dto.RepositoryListDTO
import retrofit2.http.GET

interface GithubService {

    @GET("search/repositories?q=language=+sort:stars")
    suspend fun getRepositories(): RepositoryListDTO
}