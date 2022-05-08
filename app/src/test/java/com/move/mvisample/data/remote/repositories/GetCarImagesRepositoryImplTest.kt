package com.move.mvisample.data.remote.repositories

import com.move.mvisample.data.remote.AppService
import com.move.mvisample.data.remote.NetworkResponse
import com.move.mvisample.domain.entites.CarResponse
import com.move.mvisample.presentation.viewmodel.main.MainResults
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import retrofit2.Response

class GetCarImagesRepositoryImplTest {

    lateinit var repository: GetCarImagesRepositoryImpl

    @Mock
    lateinit var appService: AppService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = GetCarImagesRepositoryImpl(appService)
    }

    @Test
    fun `test get cars in success statue`() = runBlocking {
        val params = "111"
        val response = Response.success(CarResponse(listOf()))
        val networkResponse = NetworkResponse.Success(response)

        whenever(appService.getCars(params)).thenReturn(response)

        assertEquals(networkResponse, repository.getCars(params))
    }
}