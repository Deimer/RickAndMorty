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

class FetchCharacterByNameUseCaseTest {

    @Mock
    private lateinit var characterRepository: ICharacterRepository

    private lateinit var fetchCharacterByNameUseCase: FetchCharacterByNameUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        fetchCharacterByNameUseCase = FetchCharacterByNameUseCase(characterRepository)
    }

    @Test
    fun `invoke success`() = runBlocking {
        val name = "Test Character"
        val characterModels = listOf(characterModelDummy(), characterModelDummy(2))
        `when`(characterRepository.fetchByName(name)).thenReturn(OnResult.Success(characterModels))
        val result = fetchCharacterByNameUseCase.invoke(name)
        assertEquals(OnResult.Success(characterModels), result)
    }

    @Test
    fun `invoke failure`() = runBlocking {
        val name = "Nonexistent Character"
        val exception = Exception("Test Exception")
        `when`(characterRepository.fetchByName(name)).thenReturn(OnResult.Error(exception))
        val result = fetchCharacterByNameUseCase.invoke(name)
        assertEquals(OnResult.Error(exception), result)
    }
}