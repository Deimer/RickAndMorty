package com.testvass.datasource.remote

import com.testvass.datasource.remote.character.CharacterRemoteDataSource
import com.testvass.datasource.remote.character.ICharacterRemoteDataSource
import com.testvass.datasource.utils.characterDtoDummy
import com.testvass.datasource.utils.infoDtoDummy
import com.testvass.network.api.ApiService
import com.testvass.network.dto.response.BaseResponseDTO
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class CharacterRemoteDataSourceTest {

    @Mock
    private lateinit var apiService: ApiService

    private lateinit var characterRemoteDataSource: ICharacterRemoteDataSource

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        characterRemoteDataSource = CharacterRemoteDataSource(apiService)
    }

    @Test
    fun `getCharacters success`() = runBlocking {
        val characterList = listOf(characterDtoDummy(), characterDtoDummy(2))
        val baseResponse = BaseResponseDTO(infoDtoDummy(), characterList)
        `when`(apiService.getCharacters(1)).thenReturn(baseResponse)
        val result = characterRemoteDataSource.getCharacters()
        assertEquals(characterList, result)
    }

    @Test
    fun `getCharacterById success`() = runBlocking {
        val characterId = 1
        val characterResponse = characterDtoDummy()
        `when`(apiService.getCharacterById(characterId)).thenReturn(characterResponse)
        val result = characterRemoteDataSource.getCharacterById(characterId)
        assertEquals(characterResponse, result)
    }

    @Test
    fun `getCharacterByName success`() = runBlocking {
        val characterName = "Test Character"
        val characterList = listOf(characterDtoDummy(), characterDtoDummy(2))
        val baseResponse = BaseResponseDTO(infoDtoDummy(), characterList)
        `when`(apiService.getCharacterByName(characterName)).thenReturn(baseResponse)
        val result = characterRemoteDataSource.getCharacterByName(characterName)
        assertEquals(characterList, result)
    }
}