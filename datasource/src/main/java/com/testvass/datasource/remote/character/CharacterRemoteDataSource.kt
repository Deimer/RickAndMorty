package com.testvass.datasource.remote.character

import com.testvass.network.api.ApiService
import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val apiService: ApiService
): ICharacterRemoteDataSource {

    override suspend fun getCharacters() =
        apiService.getCharacters(1).results

    override suspend fun getCharacterById(characterId: Int) =
        apiService.getCharacterById(characterId)

    override suspend fun getCharacterByName(name: String) =
        apiService.getCharacterByName(name).results
}