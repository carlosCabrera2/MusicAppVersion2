package com.example.musicapp.model


import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Results(
    @SerializedName("artistName")
    val artistName: String? = null,
    @SerializedName("artworkUrl60")
    val artworkUrl60: String? = null,
    @SerializedName("collectionName")
    val collectionName: String? = null,
    @SerializedName("trackName")
    val trackName: String? = null,
    @SerializedName("trackPrice")
    val trackPrice: Double? = null,
    @SerializedName("previewUrl")
    val previewUrl: String? = null


)