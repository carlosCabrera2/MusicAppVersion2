package com.example.musicapp.Rest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.musicapp.Utilities.UIState
import com.example.musicapp.ViewModel.MusicViewModel
import com.example.musicapp.model.MusicResponse
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException

class MusicRepositoryImplementationTest {

    private lateinit var testObject: MusicRepositoryImplementation

    private var mockServiceApi = mockk<MusicAppAPI>(relaxed = true)

    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        testObject = MusicRepositoryImplementation(mockServiceApi)
    }

    @After
    fun tearDown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }

 @Test
fun `get all songs when server returns a success response returns songs in a success state`() {
     coEvery { mockServiceApi.getAllSongs() } returns mockk {
         every { isSuccessful } returns true
         every { body() }  returns listOf(
             mockk {
                 every { dbn } returns "123"
             }
             mockk()
         )
     }
     val states = mutableListOf<UIState<List<MusicResponse>>>()

     // ACTION
     val job = testScope.launch {
         testObject.getAllSongs().collect {
             states.add(it)
         }
     }

     // ASSERTIONS
     assertEquals(2, states.size)
     assertEquals(2, (states[1] as UIState.SUCCESS).response.size)
     assertEquals("123", (states[1] as UIState.SUCCESS).response[0].dbn)

     job.cancel()
 }

    @Test(expected = FailureResponseException::class)
    fun `get all songs when server is a failure response returns  a error state`() {
        // AAA
        // ASSIGNMENT
        coEvery { mockServiceApi.getAllSongs() } returns mockk {
            every { isSuccessful } returns false
            every { errorBody() } returns mockk {
                every { string() } returns "ERROR"
            }
        }
        val states = mutableListOf<UIState<List<MusicResponse>>>()

        // ACTION
        val job = testScope.launch {
            testObject.getAllSongs().collect {
                states.add(it)
            }
        }

        // ASSERTIONS
        assertEquals(2, states.size)
        assertEquals("ERROR", (states[1] as UIState.ERROR).error.localizedMessage)

        job.cancel()
    }

    @Test(expected = HttpException::class)
    fun `get all songs when server throws an exception returns a error state`() {
        // AAA
        // ASSIGNMENT
        coEvery { mockServiceApi.getAllSongs() } throws HttpException(mockk(relaxed = true) {
            every { message() } returns "ERROR"
        })
        val states = mutableListOf<UIState<List<MusicResponse>>>()

        // ACTION
        val job = testScope.launch {
            testObject.getAllSongs().collect {
                states.add(it)
            }
        }

        // ASSERTIONS
        assertEquals(2, states.size)
        assertEquals("ERROR", (states[1] as UIState.ERROR).error.message)

        job.cancel()
    }
    }
}
