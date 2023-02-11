package com.example.musicapp.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.musicapp.model.ClassicCat.ClassicCat
import com.example.musicapp.model.PopCat.PopCat
import com.example.musicapp.model.RockCat.RockCat


@Database(
    entities = [
        ClassicCat :: class,
        PopCat :: class,
        RockCat ::class
    ],
    version = 2
)

abstract class MusicDatabase : RoomDatabase() {
    abstract fun getMusicDAO(): MusicDAO
}