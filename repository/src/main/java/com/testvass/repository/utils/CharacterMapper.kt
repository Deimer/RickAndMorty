package com.testvass.repository.utils

import com.testvass.database.entities.CharacterEntity
import com.testvass.network.dto.CharacterDTO
import com.testvass.repository.models.CharacterModel

fun CharacterDTO.toModel(): CharacterModel {
    val dto = this
    return CharacterModel(
        id = dto.id ?: 0,
        name = dto.name.orEmpty(),
        status = dto.status.orEmpty(),
        species = dto.species.orEmpty(),
        type = dto.type.orEmpty(),
        gender = dto.gender.orEmpty(),
        origin = dto.origin?.name.orEmpty(),
        location = dto.location?.name.orEmpty(),
        image = dto.image.orEmpty(),
        episodes = dto.episodes ?: emptyList(),
        episodesNumber = dto.episodes?.mapNotNull { url ->
            val episodeNumber = url.substringAfterLast('/').toInt()
            "#$episodeNumber"
        } ?: emptyList(),
        url = dto.url.orEmpty(),
        created = dto.created.orEmpty(),
        isFavorite = false
    )
}

fun CharacterDTO.toEntity(): CharacterEntity {
    val dto = this
    return CharacterEntity(
        id = dto.id ?: 0,
        name = dto.name.orEmpty(),
        status = dto.status.orEmpty(),
        species = dto.species.orEmpty(),
        type = dto.type.orEmpty(),
        gender = dto.gender.orEmpty(),
        originName = dto.origin?.name.orEmpty(),
        originUrl = dto.origin?.url.orEmpty(),
        locationName = dto.location?.name.orEmpty(),
        locationUrl = dto.location?.url.orEmpty(),
        image = dto.image.orEmpty(),
        episodes = dto.episodes?.joinToString().orEmpty(),
        url = dto.url.orEmpty(),
        created = dto.created.orEmpty(),
        isFavorite = false
    )
}

fun CharacterEntity.toModel(): CharacterModel {
    val entity = this
    return CharacterModel(
        id = entity.id,
        name = entity.name,
        status = entity.status,
        species = entity.species,
        type = entity.type,
        gender = entity.gender,
        origin = entity.originName,
        location = entity.locationName,
        image = entity.image,
        episodes = entity.episodes.split(","),
        episodesNumber = entity.episodes.split(",").map { url ->
            val episodeNumber = url.substringAfterLast('/').toInt()
            "#$episodeNumber"
        },
        url = entity.url,
        created = entity.created,
        isFavorite = entity.isFavorite
    )
}

fun CharacterModel.toEntity(): CharacterEntity {
    val model = this
    return CharacterEntity(
        id = model.id,
        name = model.name,
        status = model.status,
        species = model.species,
        type = model.type,
        gender = model.gender,
        originName = model.origin,
        locationName = model.location,
        image = model.image,
        episodes = model.episodes.joinToString(),
        url = model.url,
        created = model.created,
        isFavorite = model.isFavorite
    )
}