package com.example.musicapp.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MusicResponse(
    @Json(name = "resultCount")
    var resultCount: Int? = 0,
    @Json(name = "results")
    var results: List<Results>? = listOf()
)