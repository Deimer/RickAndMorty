package com.testvass.database.factories

import androidx.room.Database
import androidx.room.RoomDatabase
import com.testvass.database.constants.DatabaseConstants.DATABASE_VERSION
import com.testvass.database.dao.CharacterDao
import com.testvass.database.entities.CharacterEntity

@Database(entities = [CharacterEntity::class], version = DATABASE_VERSION)
abstract class RamDatabase: RoomDatabase() {

    abstract fun getCharacterDao(): CharacterDao
}