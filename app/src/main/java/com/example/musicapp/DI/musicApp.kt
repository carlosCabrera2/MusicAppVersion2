package com.example.musicapp.DI

import android.app.Application

class musicApp : Application() {

    override fun onCreate() {
        super.onCreate()
        musicComponent = DaggerMusicComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    companion object {
        lateinit var musicComponent: MusicComponent
    }
}