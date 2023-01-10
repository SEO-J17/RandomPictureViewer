package io.github.seoj17.data.data.mapper

import io.github.seoj17.data.data.network.PictureResponse
import io.github.seoj17.domain.model.DomainModel
import io.github.seoj17.domain.model.PictureEntity

fun responseToDomainModel(response: PictureResponse): DomainModel {
    return DomainModel(
        id = response.id,
        author = response.author,
        width = response.width,
        height = response.height,
        url = response.url,
        downloadUrl = response.downloadUrl
    )
}

fun entityToDomainModel(entity: PictureEntity): DomainModel {
    return DomainModel(
        id = entity.id,
        author = entity.author,
        width = entity.width,
        height = entity.height,
        url = entity.url,
        downloadUrl = entity.downloadUrl
    )
}

fun responseToEntity(response: PictureResponse): PictureEntity {
    return PictureEntity(
        id = response.id,
        author = response.author,
        width = response.width,
        height = response.height,
        url = response.url,
        downloadUrl = response.downloadUrl
    )
}

fun listEntityToDomainModel(entity: List<PictureEntity>): List<DomainModel> {
    return entity.map {
        entityToDomainModel(it)
    }
}