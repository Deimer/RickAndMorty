package com.testvass.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.testvass.database.constants.DatabaseConstants.Columns
import com.testvass.database.constants.DatabaseConstants.Columns.ID
import com.testvass.database.constants.DatabaseConstants.Tables

@Entity(tableName = Tables.CHARACTER_TABLE)
data class CharacterEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = ID)
    val id: Int,
    @ColumnInfo(name = Columns.NAME)
    val name: String,
    @ColumnInfo(name = Columns.STATUS)
    val status: String,
    @ColumnInfo(name = Columns.SPECIES)
    val species: String,
    @ColumnInfo(name = Columns.TYPE)
    val type: String,
    @ColumnInfo(name = Columns.GENDER)
    val gender: String,
    @ColumnInfo(name = Columns.ORIGIN_NAME)
    val originName: String,
    @ColumnInfo(name = Columns.ORIGIN_URL)
    val originUrl: String,
    @ColumnInfo(name = Columns.LOCATION_NAME)
    val locationName: String,
    @ColumnInfo(name = Columns.LOCATION_URL)
    val locationUrl: String,
    @ColumnInfo(name = Columns.IMAGE)
    val image: String,
    @ColumnInfo(name = Columns.EPISODES)
    val episodes: List<String>,
    @ColumnInfo(name = Columns.URL)
    val url: String,
    @ColumnInfo(name = Columns.CREATED)
    val created: String
)
