package com.example.musicapp.Utilities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicapp.Rest.MusicRepository
import com.example.musicapp.Rest.MusicRepositoryImplementation
import com.example.musicapp.ViewModel.MusicViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class MusicViewModelFactory
    @Inject constructor(
            private val musicRepository: MusicRepositoryImplementation,
            private val ioDispatcher: CoroutineDispatcher) :
    ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MusicViewModel(musicRepository, ioDispatcher) as T
            }
    }

