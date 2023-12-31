package com.testvass.repository.models

data class CharacterModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: String,
    val location: String,
    val image: String,
    val episodes: List<String>,
    val episodesNumber: List<String>,
    val url: String,
    val created: String,
    var isFavorite: Boolean
)
