package com.testvass.datasource.remote.character

import com.testvass.network.dto.CharacterDTO

interface ICharacterRemoteDataSource {

    suspend fun getCharacters(): List<CharacterDTO>

    suspend fun getCharacterById(
        characterId: Int
    ): CharacterDTO

    suspend fun getCharacterByName(
        name: String
    ): List<CharacterDTO>
}