package com.example.musicapp.DI

import com.example.musicapp.Rest.MusicRepository
import com.example.musicapp.Rest.MusicRepositoryImplementation
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun providesMusicRepository(
        musicRepositoryImpl: MusicRepositoryImplementation):
            MusicRepository

}