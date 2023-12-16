package com.testvass.network.dto

import com.google.gson.annotations.SerializedName

data class ComplementDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
