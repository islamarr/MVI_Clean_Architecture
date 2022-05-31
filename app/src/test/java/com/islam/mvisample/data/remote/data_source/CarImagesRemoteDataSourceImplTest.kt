package com.islam.mvisample.data.remote.data_source

import com.islam.mvisample.data.remote.NetworkResponse
import com.islam.mvisample.data.remote.api.ApiService
import com.islam.mvisample.domain.entites.CarResponse
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import retrofit2.Response

class CarImagesRemoteDataSourceImplTest {

    private lateinit var dataSource: CarImagesRemoteDataSourceImpl

    @Mock
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        dataSource = CarImagesRemoteDataSourceImpl(apiService)
    }

    @Test
    fun `when get cars return success response`() = runBlocking {
        val params = "111"
        val response = Response.success(CarResponse(listOf()))
        val networkResponse = NetworkResponse.Success(response)

        whenever(apiService.getCars(params)).thenReturn(response)

        assertEquals(networkResponse, dataSource.getCars(params))
    }

    @Test
    fun `when get cars return error response`() = runBlocking {
        val params = "111"
        val response = Response.error<CarResponse>(
            500,
            "{\"key\":[\"something\"]}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
        val networkResponse = NetworkResponse.Success(response)

        whenever(apiService.getCars(params)).thenReturn(response)

        assertEquals(networkResponse, dataSource.getCars(params))
    }

}