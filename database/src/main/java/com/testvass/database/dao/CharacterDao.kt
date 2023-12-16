package com.testvass.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.testvass.database.constants.DatabaseConstants.Columns.ID
import com.testvass.database.constants.DatabaseConstants.Columns.NAME
import com.testvass.database.constants.DatabaseConstants.Tables.CHARACTER_TABLE
import com.testvass.database.entities.CharacterEntity

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characters: List<CharacterEntity>)

    @Update
    suspend fun update(character: CharacterEntity)

    @Delete
    suspend fun delete(character: CharacterEntity)

    @Query("DELETE FROM $CHARACTER_TABLE")
    suspend fun deleteAll()

    @Query("SELECT * FROM $CHARACTER_TABLE")
    suspend fun fetchAll(): List<CharacterEntity>

    @Query("SELECT * FROM $CHARACTER_TABLE WHERE $ID = :characterId")
    suspend fun fetchById(characterId: Int): CharacterEntity

    @Query("SELECT * FROM $CHARACTER_TABLE WHERE $NAME LIKE :name")
    suspend fun fetchByName(name: String): List<CharacterEntity>
}