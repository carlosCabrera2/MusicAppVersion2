package com.example.musicapp.model.PopCat

import androidx.room.Entity
import androidx.room.PrimaryKey

import com.example.musicapp.model.Results

@Entity
data class PopCat(
    @PrimaryKey

    val artistName: String,
    val collectionName: String,
    val artworkUrl60: String,
    val trackPrice: Double


)

fun Results?.maptoPopCat(): PopCat = PopCat(

    artistName = this?.artistName?: "-",
    collectionName = this?.collectionName?: "-",
    artworkUrl60 = this?.artworkUrl60?: "-",
    trackPrice = (this?.trackPrice?: "0.0") as Double


)