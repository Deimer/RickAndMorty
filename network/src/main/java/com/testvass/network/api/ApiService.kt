package com.testvass.network.api

import com.testvass.network.constants.NetworkConstants.PARAMETERS.PARAMETER_CHARACTER
import com.testvass.network.constants.NetworkConstants.PARAMETERS.PARAMETER_NAME
import com.testvass.network.constants.NetworkConstants.PARAMETERS.PARAMETER_PAGE
import com.testvass.network.constants.NetworkConstants.URLs.CHARACTERS_PATH
import com.testvass.network.constants.NetworkConstants.URLs.CHARACTER_PATH
import com.testvass.network.dto.CharacterDTO
import com.testvass.network.dto.response.BaseResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(CHARACTERS_PATH)
    suspend fun getCharacters(
        @Query(PARAMETER_PAGE) page: Int
    ): BaseResponseDTO<List<CharacterDTO>>

    @GET(CHARACTER_PATH)
    suspend fun getCharacterById(
        @Path(PARAMETER_CHARACTER) characterId: Int
    ): CharacterDTO

    @GET(CHARACTER_PATH)
    suspend fun getCharacterByName(
        @Query(PARAMETER_NAME) name: String
    ): BaseResponseDTO<List<CharacterDTO>>
}