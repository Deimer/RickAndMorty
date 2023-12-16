package com.testvass.datasource.local.character

import com.testvass.database.entities.CharacterEntity

interface ICharacterLocalDataSource {

    suspend fun fetchById(
        characterId: Int
    ): CharacterEntity

    suspend fun fetchByName(
        name: String
    ): List<CharacterEntity>

    suspend fun fetch(): List<CharacterEntity>

    suspend fun insert(characters: List<CharacterEntity>)

    suspend fun delete(character: CharacterEntity)

    suspend fun update(character: CharacterEntity)
}