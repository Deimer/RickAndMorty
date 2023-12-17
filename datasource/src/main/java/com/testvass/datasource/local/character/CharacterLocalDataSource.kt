package com.testvass.datasource.local.character

import com.testvass.database.dao.CharacterDao
import com.testvass.database.entities.CharacterEntity
import javax.inject.Inject

class CharacterLocalDataSource @Inject constructor(
    private val characterDao: CharacterDao
): ICharacterLocalDataSource {

    override suspend fun fetchById(characterId: Int) =
        characterDao.fetchById(characterId)

    override suspend fun fetchByName(name: String) =
        characterDao.fetchByName(name)

    override suspend fun fetch() =
        characterDao.fetchAll()

    override suspend fun insert(characters: List<CharacterEntity>) {
        characterDao.insert(characters)
    }

    override suspend fun delete(character: CharacterEntity) {
        characterDao.delete(character)
    }

    override suspend fun update(character: CharacterEntity) {
        characterDao.update(character)
    }
}