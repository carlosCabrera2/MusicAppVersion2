package com.example.musicapp.Rest
import android.util.Log
import com.example.musicapp.Utilities.UIState
import com.example.musicapp.model.MusicResponse
import com.example.musicapp.model.SongEnum
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val TAG = "MusicRepository"

interface MusicRepository {
    fun getListByType(genre: SongEnum): Flow<UIState<MusicResponse>>
}

class MusicRepositoryImplementation @Inject constructor(
    private val api: MusicAppAPI,

    ) : MusicRepository {
    override fun getListByType(genre: SongEnum): Flow<UIState<MusicResponse>> = flow {
        emit(UIState.LOADING)
        try {
            val response = api.getAllSongs(genre)
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d(TAG, "getListByGenre: $it")
                    emit(UIState.SUCCESS(it))
                } ?: throw NullPopResponse()
            } else
                throw FailureResponse(response.errorBody()?.string())
        } catch (e: Exception) {
            Log.e(TAG, "getListByGenre: $e")
            emit(UIState.ERROR(e))
        }
    }
}