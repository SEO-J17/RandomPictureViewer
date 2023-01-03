package io.github.seoj17.data.mapper

import io.github.seoj17.data.network.PictureResponse
import io.github.seoj17.doamain.model.DomainPicture
import io.github.seoj17.doamain.model.PictureEntity

fun responseToDomainModel(response: PictureResponse): DomainPicture {
    return DomainPicture(
        id = response.id,
        author = response.author,
        width = response.width,
        height = response.height,
        url = response.url,
        downloadUrl = response.downloadUrl
    )
}

fun entityToDomainModel(entity: PictureEntity): DomainPicture {
    return DomainPicture(
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

fun listEntityToDomainModel(entity: List<PictureEntity>): List<DomainPicture> {
    return entity.map {
        entityToDomainModel(it)
    }
}
