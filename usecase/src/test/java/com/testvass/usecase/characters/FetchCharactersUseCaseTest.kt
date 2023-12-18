package com.testvass.usecase.characters

import com.testvass.repository.repositories.character.ICharacterRepository
import com.testvass.repository.utils.OnResult
import com.testvass.usecase.utils.characterModelDummy
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class FetchCharactersUseCaseTest {

    @Mock
    private lateinit var characterRepository: ICharacterRepository

    private lateinit var fetchCharactersUseCase: FetchCharactersUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        fetchCharactersUseCase = FetchCharactersUseCase(characterRepository)
    }

    @Test
    fun `invoke success`() = runBlocking {
        val characterModels = listOf(characterModelDummy(), characterModelDummy(2))
        `when`(characterRepository.fetch()).thenReturn(OnResult.Success(characterModels))
        val result = fetchCharactersUseCase.invoke()
        assertEquals(OnResult.Success(characterModels), result)
    }

    @Test
    fun `invoke failure`() = runBlocking {
        val exception = Exception("Test Exception")
        `when`(characterRepository.fetch()).thenReturn(OnResult.Error(exception))
        val result = fetchCharactersUseCase.invoke()
        assertEquals(OnResult.Error(exception), result)
    }
}
