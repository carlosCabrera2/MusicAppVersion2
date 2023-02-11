package com.example.musicapp.Rest

import com.example.musicapp.model.ClassicCat.ClassicCat
import com.example.musicapp.model.MusicResponse
import com.example.musicapp.model.PopCat.PopCat
import com.example.musicapp.model.RockCat.RockCat
import com.example.musicapp.model.SongEnum
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MusicAppAPI {



    @GET(SEARCH)
    suspend fun getAllSongs(@Query("term") sogGenre:SongEnum): Response<MusicResponse>


    companion object {

        const val BASE_URL= "https://itunes.apple.com/"
        private const val SEARCH = "search"

    }


}