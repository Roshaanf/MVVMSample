package com.roshaan.githubapp.data.mapper

import com.roshaan.githubapp.data.db.entity.RepositoryEntity
import com.roshaan.githubapp.data.dto.RepositoryListDTO
import com.roshaan.githubapp.data.model.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataMapper @Inject constructor() {

    fun transformEntityListToRepositoryList(list: List<RepositoryEntity>) =
        list.map {
            Repository(
                it.ownerName,
                it.fullName,
                it.description,
                it.avatarUrl,
                it.stars
            )
        }

    fun transformRepositoryListDTOToRepositoryEntityList(listDto: RepositoryListDTO) =
        listDto.items.map {
            RepositoryEntity(
                id = it.id,
                ownerName = it.owner.login,
                fullName = it.full_name,
                description = it.description,
                avatarUrl = it.owner.avatar_url,
                stars = it.stargazers_count
            )
        }
}