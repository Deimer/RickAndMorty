package com.testvass.network.constants

object NetworkConstants {

    object DEFAULTS {
        const val DEFAULT_TIMEOUT = 10L
        const val DEFAULT_LIMIT = 20
    }

    object PARAMETERS {
        const val PARAMETER_PAGE = "page"
        const val PARAMETER_CHARACTER = "characterId"
        const val PARAMETER_NAME = "name"
    }

    object URLs {
        const val CHARACTERS_PATH = "character"
        const val CHARACTER_PATH = "character/{characterId}"
        const val LOCATIONS_PATH = "location"
        const val LOCATION_PATH = "location/{locationId}"
        const val EPISODES_PATH = "episode"
        const val EPISODE_PATH = "episode/{episodeId}"
    }
}