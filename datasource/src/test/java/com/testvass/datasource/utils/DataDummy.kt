package com.testvass.datasource.utils

import com.testvass.database.entities.CharacterEntity
import com.testvass.network.dto.CharacterDTO
import com.testvass.network.dto.ComplementDTO
import com.testvass.network.dto.InfoDTO

fun characterEntityDummy(id: Int = 1) = CharacterEntity(
    id = id,
    name = "Local Character",
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

fun characterDtoDummy(id: Int = 1) = CharacterDTO(
    id = id,
    name = "Remote Character",
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

fun infoDtoDummy() = InfoDTO(0, 0, "", "")