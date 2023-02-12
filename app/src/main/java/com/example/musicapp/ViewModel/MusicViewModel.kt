package com.example.musicapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.Rest.MusicRepository
import com.example.musicapp.Rest.MusicRepositoryImplementation
import com.example.musicapp.Utilities.UIState
import com.example.musicapp.model.MusicResponse
import com.example.musicapp.model.SongEnum
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MusicViewModel (
    private val musicRepository: MusicRepositoryImplementation,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
 ): ViewModel(){


    private val songs = SongEnum.values()
    private val _classicCat: MutableLiveData<UIState<MusicResponse>> = MutableLiveData(UIState.LOADING)
    val classicCat: LiveData<UIState<MusicResponse>> get() = _classicCat

    private val _popCat: MutableLiveData<UIState<MusicResponse>> = MutableLiveData(UIState.LOADING)
    val popCat: LiveData<UIState<MusicResponse>> get() = _popCat

    // Use rockCat as a reference
    private val _rockCat: MutableLiveData<UIState<MusicResponse>> = MutableLiveData(UIState.LOADING)
    val rockCat: LiveData<UIState<MusicResponse>> get() = _rockCat

     init {
         getSong()

     }

    lateinit var musicTrack: String


    private fun getSong() {
        songs.forEach { g ->
            run {
                viewModelScope.launch(ioDispatcher) {
                    musicRepository.getListByType(g).collect() {
                        when (g) {
                            SongEnum.CLASSIC -> _classicCat.postValue(it)
                            SongEnum.POP -> _popCat.postValue(it)
                            SongEnum.ROCK -> _rockCat.postValue(it)

                        }
                    }
                }
            }
        }


    }

    fun setTrack(musicTrack: String){
            this.musicTrack = musicTrack
    }

}