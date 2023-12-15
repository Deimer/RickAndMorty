package com.testvass.database.constants

import com.testvass.database.BuildConfig

object DatabaseConstants {

    const val DATABASE_VERSION = 1
    const val KEY_NAME_DATABASE = BuildConfig.DATABASE_NAME

    object Tables {
        const val CHARACTER_TABLE = "character_table"
        const val LOCATION_TABLE = "location_table"
        const val EPISODE_TABLE = "episode_table"
    }

    object Columns {
        const val ID = "id"
        const val NAME = "name"
        const val STATUS = "status"
        const val SPECIES = "species"
        const val TYPE = "type"
        const val GENDER = "gender"
        const val ORIGIN_NAME = "origin_name"
        const val ORIGIN_URL = "origin_url"
        const val LOCATION_NAME = "location_name"
        const val LOCATION_URL = "location_url"
        const val IMAGE = "image"
        const val EPISODES = "episodes"
        const val URL = "url"
        const val CREATED = "created"
        const val DIMENSION = "dimension"
        const val RESIDENTS = "residents"
        const val AIR_DATE = "air_date"
        const val EPISODE = "episode"
        const val CHARACTERS = "characters"
        const val IS_FAVORITE = "isFavorite"
    }
}