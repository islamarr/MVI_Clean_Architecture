package com.islam.mvisample.presentation.viewmodel.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.islam.mvisample.MainCoroutineRule
import com.islam.mvisample.domain.entites.CarImage
import com.islam.mvisample.domain.usecases.GetCarImageUrlUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var mainUseCase: GetCarImageUrlUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mainViewModel = MainViewModel(mainUseCase)
    }

    @Test
    fun `when load images return Loading result as a first result`() = runBlocking {
        val id = "111"

        val actual = mainViewModel.handle(MainActions.LoadImages(id)).first()
        val expected = MainResults.Loading

        assertEquals(actual, expected)
    }

    @Test
    fun `when load images return CarImageURLListLoaded result as a second result`() = runBlocking {
        val id = "111"
        whenever(mainUseCase.execute(id)).thenReturn(MainResults.CarImageURLListLoaded(listOf()))

        val actual = mainViewModel.handle(MainActions.LoadImages(id)).drop(1).first()
        val expected = MainResults.CarImageURLListLoaded(listOf())
        verify(mainUseCase, times(1)).execute(id)
        verifyNoMoreInteractions(mainUseCase)

        assertEquals(actual, expected)
    }

    @Test
    fun `when start viewModel return InitialState`() = runBlocking {
        val expected = MainStates.InitialState
        val actual = mainViewModel.state.first()

        assertEquals(expected, actual)
    }

    @Test
    fun `when dispatch viewModel return CarImagesLoaded state`() = runBlocking {
        val id = "111"
        val action = MainActions.LoadImages(id)
        val imageList = listOf(CarImage(""))

        val job = launch {
            mainViewModel.state.collect { }
        }
        whenever(mainUseCase.execute(id)).thenReturn(MainResults.CarImageURLListLoaded(imageList))
        mainViewModel.dispatch(action)

        val actual = mainViewModel.state.first()
        val expected = MainStates.CarImagesLoaded(imageList)

        assertEquals(expected, actual)
        job.cancel()
    }

    @Test
    fun `when reduce error result return ShowErrorMessage state`() = runBlocking {

        val actual = mainViewModel.reduce(MainResults.Error("reason", 501))
        val expected = MainStates.ShowErrorMessage("reason", 501)

        assertEquals(actual, expected)
    }

    @Test
    fun `when reduce CarImageURLEmptyList result return EmptyCarList state`() = runBlocking {

        val actual = mainViewModel.reduce(MainResults.CarImageURLEmptyList)
        val expected = MainStates.EmptyCarList

        assertEquals(actual, expected)
    }

    @Test
    fun `when reduce CarImageURLListLoaded result return CarImagesLoaded state`() = runBlocking {
        val imageList = listOf(CarImage(""))

        val actual = mainViewModel.reduce(MainResults.CarImageURLListLoaded(imageList))
        val expected = MainStates.CarImagesLoaded(imageList)

        assertEquals(actual, expected)
    }

    @Test
    fun `when reduce UnExpectedError result return ShowErrorMessage state`() = runBlocking {

        val actual = mainViewModel.reduce(MainResults.UnExpectedError)
        val expected = MainStates.ShowErrorMessage()

        assertEquals(actual, expected)
    }

}