package com.example.musicapp.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.musicapp.model.ClassicCat.ClassicCat
import com.example.musicapp.model.PopCat.PopCat
import com.example.musicapp.model.RockCat.RockCat


@Dao
interface MusicDAO {
    @Query("SELECT * FROM Rock Category")
    suspend fun getRockCat(): List<RockCat>

    @Query("SELECT * FROM Classic Category")
    suspend fun getClassicCat(): List<ClassicCat>

    @Query("SELECT * FROM List")
    suspend fun getPopCat(): List<PopCat>


    @Insert(
        entity = ClassicCat :: class,
        onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClassicCat(vararg classicCat: ClassicCat)

    @Insert(
        entity = RockCat :: class,
        onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRockCat(vararg rockCat: RockCat)

    @Insert(
        entity = PopCat ::class,
        onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopCat(vararg popCat: PopCat)
}