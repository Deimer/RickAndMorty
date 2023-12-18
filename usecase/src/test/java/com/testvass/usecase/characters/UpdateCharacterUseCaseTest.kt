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

class UpdateCharacterUseCaseTest {

    @Mock
    private lateinit var characterRepository: ICharacterRepository

    private lateinit var updateCharacterUseCase: UpdateCharacterUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        updateCharacterUseCase = UpdateCharacterUseCase(characterRepository)
    }

    @Test
    fun `invoke success`() = runBlocking {
        val characterModel = characterModelDummy()
        `when`(characterRepository.update(characterModel)).thenReturn(OnResult.Success(true))
        val result = updateCharacterUseCase.invoke(characterModel)
        assertEquals(OnResult.Success(true), result)
    }

    @Test
    fun `invoke failure`() = runBlocking {
        val characterModel = characterModelDummy()
        val exception = Exception("Test Exception")
        `when`(characterRepository.update(characterModel)).thenReturn(OnResult.Error(exception))
        val result = updateCharacterUseCase.invoke(characterModel)
        assertEquals(OnResult.Error(exception), result)
    }
}
