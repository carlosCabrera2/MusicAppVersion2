package com.example.musicapp.DI

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.musicapp.Database.MusicDAO
import com.example.musicapp.Database.MusicDatabase
import com.example.musicapp.Database.migration_1_2
import com.example.musicapp.Rest.MusicRepository
import com.example.musicapp.Rest.MusicRepositoryImplementation
import com.example.musicapp.Utilities.MusicViewModelFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    fun providesContext(): Context = application.applicationContext

    @Provides
    fun providesMusicDatabase(context: Context):
        MusicDatabase = Room.databaseBuilder(
        context,
         MusicDatabase::class.java,
         "music-db").addMigrations(migration_1_2)
               .build()

    @Provides
    fun providesMusicDAO(
        musicDatabase: MusicDatabase
    ): MusicDAO = musicDatabase.getMusicDAO()


    @Provides
    fun provideViewModelFactory(
        repository: MusicRepositoryImplementation,
        ioDispatcher: CoroutineDispatcher
        ): MusicViewModelFactory =
            MusicViewModelFactory(repository, ioDispatcher)
}
