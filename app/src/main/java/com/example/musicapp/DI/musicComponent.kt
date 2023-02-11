package com.example.musicapp.DI

import android.app.Application
import com.example.musicapp.MainActivity
import com.example.musicapp.Utilities.BaseFragment
import dagger.Component

@Component( modules=[
    NetworkModule :: class,
    RepositoryModule :: class,
    ApplicationModule :: class
])

interface MusicComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(baseFragment: BaseFragment)
}