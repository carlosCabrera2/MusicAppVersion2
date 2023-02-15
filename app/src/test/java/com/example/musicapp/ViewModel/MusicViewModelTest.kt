package com.example.musicapp.ViewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.musicapp.Rest.MusicRepository
import com.example.musicapp.Utilities.UIState
import com.example.musicapp.model.MusicResponse
import io.mockk.clearAllMocks
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MusicViewModelTest {

    private lateinit var testObject: MusicViewModel

    private var mockRepository = mockk<MusicRepository>(relaxed = true)
    private var mockDispatcher = UnconfinedTestDispatcher()

    @get:Rule

    val instantTask = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        Dispatchers.setMain(mockDispatcher)
        testObject = MusicViewModel(mockRepository,mockDispatcher)
    }

    @After
    fun tearDown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }

    @Test
    fun `get Pop songs when repository gets the list of musicResponse and returns a success state`() {
        every{mockRepository.getMusic()} returns flowOf(
            UIState.SUCCESS(listof( mockk(), mockk(), mockk()))
            )
         testObject.popCat.observeForever {
             when(it){
                 is UIState.LOADING ->{}
                 is UIState.SUCCESS->{assertEquals(5 , it.response.results)}
                 is UIState.ERROR->{}
             }
         }

        val list = mutableListOf<MusicResponse>()
        testObject.popCat.observeForever { list.addAll(it!!) }

        testObject.getSong

        assertEquals(5, list.size)

    }

    @Test
    fun `get Rock songs when repository gets the list of musicResponse and returns a success state`() {
        every{mockRepository.getMusic()} returns flowOf(
            UIState.SUCCESS(listof( mockk(), mockk(), mockk()))
        )
        testObject.rockCat.observeForever {
            when(it){
                is UIState.LOADING ->{}
                is UIState.SUCCESS->{assertEquals(5 , it.response.results)}
                is UIState.ERROR->{}
            }
        }

        val list = mutableListOf<MusicResponse>()
        testObject.rockCat.observeForever { list.addAll(it!!) }

        testObject.getSong

        assertEquals(5, list.size)
    }

    @Test
    fun `get Classic songs when repository gets the list of musicResponse and returns a success state`() {
        every{mockRepository.getMusic()} returns flowOf(
            UIState.SUCCESS(listof( mockk(), mockk(), mockk()))
        )
        testObject.classicCat.observeForever {
            when(it){
                is UIState.LOADING ->{}
                is UIState.SUCCESS->{assertEquals(5 , it.response.results)}
                is UIState.ERROR->{}
            }
        }

        val list = mutableListOf<MusicResponse>()
        testObject.classicCat.observeForever { list.addAll(it!!) }

        testObject.getSong

        assertEquals(5, list.size)
    }


}