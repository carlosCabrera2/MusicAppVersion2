package com.example.musicapp.model.Items

import com.example.musicapp.model.Results
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailsResponse(
    @Json(name = "resultCount")
    val resultCount: Int? = 0,
    @Json(name = "results")
    val results: List<Results>? = listOf()

)
