package com.testvass.network.dto.response

import com.google.gson.annotations.SerializedName
import com.testvass.network.dto.CharacterDTO
import com.testvass.network.dto.InfoDTO

data class ResponseCharacterDTO(
    @SerializedName("info")
    val info: InfoDTO,
    @SerializedName("results")
    val results: List<CharacterDTO>
)
