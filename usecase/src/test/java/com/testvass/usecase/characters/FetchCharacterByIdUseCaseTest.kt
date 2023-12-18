package com.testvass.usecase.characters

import com.testvass.repository.repositories.character.ICharacterRepository
import com.testvass.repository.utils.OnResult
import com.testvass.usecase.utils.characterModelDummy
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class FetchCharacterByIdUseCaseTest {

    @Mock
    private lateinit var characterRepository: ICharacterRepository

    private lateinit var fetchCharacterByIdUseCase: FetchCharacterByIdUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        fetchCharacterByIdUseCase = FetchCharacterByIdUseCase(characterRepository)
    }

    @Test
    fun `invoke success`() = runBlocking {
        val characterId = 1
        val characterModel = characterModelDummy()
        `when`(characterRepository.fetchById(characterId)).thenReturn(OnResult.Success(characterModel))
        val result = fetchCharacterByIdUseCase.invoke(characterId)
        assertEquals(OnResult.Success(characterModel), result)
    }

    @Test
    fun `invoke failure`() = runBlocking {
        val characterId = 1
        val exception = Exception("Test Exception")
        `when`(characterRepository.fetchById(characterId)).thenReturn(OnResult.Error(exception))
        val result = fetchCharacterByIdUseCase.invoke(characterId)
        assertEquals(OnResult.Error(exception), result)
    }
}