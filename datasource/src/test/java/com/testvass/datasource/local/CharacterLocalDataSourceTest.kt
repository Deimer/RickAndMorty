package com.testvass.datasource.local

import com.testvass.database.dao.CharacterDao
import com.testvass.datasource.local.character.CharacterLocalDataSource
import com.testvass.datasource.local.character.ICharacterLocalDataSource
import com.testvass.datasource.utils.characterEntityDummy
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class CharacterLocalDataSourceTest {

    @Mock
    private lateinit var characterDao: CharacterDao

    private lateinit var characterLocalDataSource: ICharacterLocalDataSource

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        characterLocalDataSource = CharacterLocalDataSource(characterDao)
    }

    @Test
    fun `fetchById success`() = runBlocking {
        val characterId = 1
        val characterEntity = characterEntityDummy()
        `when`(characterDao.fetchById(characterId)).thenReturn(characterEntity)
        val result = characterLocalDataSource.fetchById(characterId)
        assertEquals(characterEntity, result)
    }

    @Test
    fun `fetchByName success`() = runBlocking {
        val name = "Test Character"
        val characterEntityList = listOf(characterEntityDummy(), characterEntityDummy(2))
        `when`(characterDao.fetchByName(name)).thenReturn(characterEntityList)
        val result = characterLocalDataSource.fetchByName(name)
        assertEquals(characterEntityList, result)
    }

    @Test
    fun `fetch success`() = runBlocking {
        val characterEntityList = listOf(characterEntityDummy(), characterEntityDummy(2))
        `when`(characterDao.fetchAll()).thenReturn(characterEntityList)
        val result = characterLocalDataSource.fetch()
        assertEquals(characterEntityList, result)
    }

    @Test
    fun `insert success`() = runBlocking {
        val characterEntityList = listOf(characterEntityDummy(), characterEntityDummy(2))
        characterLocalDataSource.insert(characterEntityList)
        Mockito.verify(characterDao).insert(characterEntityList)
    }

    @Test
    fun `delete success`() = runBlocking {
        val characterEntity = characterEntityDummy()
        characterLocalDataSource.delete(characterEntity)
        Mockito.verify(characterDao).delete(characterEntity)
    }

    @Test
    fun `update success`() = runBlocking {
        val characterEntity = characterEntityDummy()
        characterLocalDataSource.update(characterEntity)
        Mockito.verify(characterDao).update(characterEntity)
    }
}