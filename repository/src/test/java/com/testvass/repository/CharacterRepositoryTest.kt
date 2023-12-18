package com.testvass.repository

import com.testvass.datasource.local.character.ICharacterLocalDataSource
import com.testvass.datasource.remote.character.ICharacterRemoteDataSource
import com.testvass.repository.repositories.character.CharacterRepository
import com.testvass.repository.repositories.character.ICharacterRepository
import com.testvass.repository.utils.OnResult
import com.testvass.repository.utils.characterDtoDummy
import com.testvass.repository.utils.characterEntityDummy
import com.testvass.repository.utils.characterModelDummy
import com.testvass.repository.utils.toEntity
import com.testvass.repository.utils.toModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.io.IOException

class CharacterRepositoryTest {

    private lateinit var characterLocalDataSource: ICharacterLocalDataSource
    private lateinit var characterRemoteDataSource: ICharacterRemoteDataSource

    private lateinit var characterRepository: ICharacterRepository

    @Before
    fun setup() {
        characterLocalDataSource = mock(ICharacterLocalDataSource::class.java)
        characterRemoteDataSource = mock(ICharacterRemoteDataSource::class.java)
        characterRepository = CharacterRepository(characterLocalDataSource, characterRemoteDataSource)
    }

    @Test
    fun `fetch success`() = runBlocking {
        val localCharacters = listOf(characterEntityDummy())
        `when`(characterLocalDataSource.fetch()).thenReturn(localCharacters)
        val remoteCharacters = listOf(characterDtoDummy())
        `when`(characterRemoteDataSource.getCharacters()).thenReturn(remoteCharacters)
        val result = characterRepository.fetch()
        assertEquals(OnResult.Success(localCharacters.map { it.toModel() }), result)
    }

    @Test
    fun `fetch failure`() = runBlocking {
        `when`(characterLocalDataSource.fetch()).thenReturn(emptyList())
        `when`(characterRemoteDataSource.getCharacters()).thenAnswer {
            throw IOException("Network error")
        }
        val result = characterRepository.fetch()
        assertThat(result, instanceOf(OnResult.Error::class.java))
        assertThat((result as OnResult.Error).exception, instanceOf(IOException::class.java))
        assertThat(result.exception.message, equalTo("Network error"))
    }

    @Test
    fun `update success`() = runBlocking {
        val characterModel = characterModelDummy()
        `when`(characterLocalDataSource.update(characterModel.toEntity())).thenReturn(Unit)
        val result = characterRepository.update(characterModel)
        assertThat(result, instanceOf(OnResult.Success::class.java))
        assertThat((result as OnResult.Success).data, equalTo(true))
    }

    @Test
    fun `delete success`() = runBlocking {
        val characterModel = characterModelDummy()
        `when`(characterLocalDataSource.delete(characterModel.toEntity())).thenReturn(Unit)
        val result = characterRepository.delete(characterModel)
        assertThat(result, instanceOf(OnResult.Success::class.java))
        assertThat((result as OnResult.Success).data, equalTo(true))
    }

    @Test
    fun `fetchById success`() = runBlocking {
        val characterEntity = characterEntityDummy()
        `when`(characterLocalDataSource.fetchById(1)).thenReturn(characterEntity)
        val result = characterRepository.fetchById(1)
        assertThat(result, instanceOf(OnResult.Success::class.java))
        assertThat((result as OnResult.Success).data, equalTo(characterEntity.toModel()))
    }

    @Test
    fun `fetchByName success`() = runBlocking {
        val remoteCharacters = listOf(characterDtoDummy())
        `when`(characterRemoteDataSource.getCharacterByName("Remote")).thenReturn(remoteCharacters)
        val result = characterRepository.fetchByName("Remote")
        assertThat(result, instanceOf(OnResult.Success::class.java))
        assertThat((result as OnResult.Success).data, equalTo(remoteCharacters.map { it.toModel() }))
    }

    @Test
    fun `fetchByName failure`() = runBlocking {
        `when`(characterRemoteDataSource.getCharacterByName("Remote"))
            .thenAnswer { throw IOException("Network error") }
        val result = characterRepository.fetchByName("Remote")
        assertThat(result, instanceOf(OnResult.Error::class.java))
        assertThat((result as OnResult.Error).exception, instanceOf(IOException::class.java))
        assertThat(result.exception.message, equalTo("Network error"))
    }
}