package com.example.musicapp.model.ClassicCat

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.musicapp.model.Results

@Entity
data class ClassicCat(
    @PrimaryKey

    val artistName: String,
    val collectionName: String,
    val artworkUrl60: String,
    val trackPrice: Double


)

fun Results?.maptoClassicCat(): ClassicCat = ClassicCat(

    artistName = this?.artistName?: "-",
    collectionName = this?.collectionName?: "-",
    artworkUrl60 = this?.artworkUrl60?: "-",
    trackPrice = (this?.trackPrice?: "0.0") as Double

)