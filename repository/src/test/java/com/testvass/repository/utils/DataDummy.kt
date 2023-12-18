package com.testvass.repository.utils

import com.testvass.database.entities.CharacterEntity
import com.testvass.network.dto.CharacterDTO
import com.testvass.network.dto.ComplementDTO
import com.testvass.repository.models.CharacterModel

fun characterModelDummy() = CharacterModel(
    id = 1,
    name = "Updated Character",
    status = "status",
    species = "species",
    type = "type",
    gender = "gender",
    origin = "origin",
    location = "location",
    image = "image",
    episodes = listOf("episodes"),
    episodesNumber = listOf("#1", "#2"),
    url = "url",
    created = "created",
    isFavorite = false
)

fun characterEntityDummy() = CharacterEntity(
    id = 1, name = "Local Character",
    status = "status",
    species = "species",
    type = "type",
    gender = "gender",
    originName = "originName",
    originUrl = "originUrl",
    locationName = "locationName",
    locationUrl = "locationUrl",
    image = "image",
    episodes = "https://rickandmortyapi.com/api/episode/1,https://rickandmortyapi.com/api/episode/2",
    url = "url",
    created = "created",
    isFavorite = false
)

fun characterDtoDummy() = CharacterDTO(
    2,
    "Remote Character",
    status = "status",
    species = "species",
    type = "type",
    gender = "gender",
    origin = ComplementDTO("", ""),
    location = ComplementDTO("", ""),
    image = "image",
    episodes = listOf("https://rickandmortyapi.com/api/episode/1", "https://rickandmortyapi.com/api/episode/2"),
    url = "url",
    created = "created"
)