package com.testvass.usecase.utils

import com.testvass.repository.models.CharacterModel

fun characterModelDummy(id: Int = 1) = CharacterModel(
    id = id,
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