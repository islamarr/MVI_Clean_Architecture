package com.move.mvisample.presentation.viewmodel.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.move.mvisample.MainCoroutineRule
import com.move.mvisample.domain.entites.CarImage
import com.move.mvisample.domain.usecases.GetCarImageUrlUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

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
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var mainUseCase: GetCarImageUrlUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mainViewModel = MainViewModel(mainUseCase)
    }

    @Test
    fun handleLoadAction() = runBlocking {
        val id = "111"
        whenever(mainUseCase.execute(id)).thenReturn(MainResults.ImageURL(listOf()))

        val actual = mainViewModel.handle(MainActions.LoadImages(id)).first()
        val expected = MainResults.ImageURL(listOf())
        verify(mainUseCase, times(1)).execute(id)
        verifyNoMoreInteractions(mainUseCase)

        assertEquals(actual, expected)
    }

    @Test
    fun reduceErrorResult() = runBlocking {

        val actual = mainViewModel.reduce(MainResults.ERROR("", 501))
        val expected = MainStates.ShowERRORMessage("", 501)

        assertEquals(actual, expected)
    }

    @Test
    fun reduceEmptyListResult() = runBlocking {

        val actual = mainViewModel.reduce(MainResults.EmptyList)
        val expected = MainStates.EmptyCarList

        assertEquals(actual, expected)
    }

    @Test
    fun reduceLoadResult() = runBlocking {

        val actual = mainViewModel.reduce(MainResults.ImageURL(listOf(CarImage(""))))
        val expected = MainStates.CarImagesLoaded(listOf(CarImage("")))

        assertEquals(actual, expected)
    }
}