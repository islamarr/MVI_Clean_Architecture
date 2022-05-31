package com.islam.mvisample.data.repositories

import com.islam.mvisample.data.remote.NetworkResponse
import com.islam.mvisample.data.remote.data_source.CarImagesRemoteDataSource
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

class CarImagesRepositoryImplTest {

    private lateinit var repository: CarImagesRepositoryImpl

    @Mock
    private lateinit var remoteDataSource: CarImagesRemoteDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = CarImagesRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `test get cars in success statue`() = runBlocking {
        val params = "111"
        val response = Response.success(CarResponse(listOf()))
        val networkResponse = NetworkResponse.Success(response)

        whenever(remoteDataSource.getCars(params)).thenReturn(networkResponse)

        assertEquals(networkResponse, repository.getCars(params))
    }

    @Test
    fun `when get cars return error response`() = runBlocking {
        val params = "111"
        val response = Response.error<CarResponse>(
            400,
            "{\"key\":[\"something\"]}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
        val networkResponse = NetworkResponse.Success(response)

        whenever(remoteDataSource.getCars(params)).thenReturn(networkResponse)

        assertEquals(networkResponse, remoteDataSource.getCars(params))
    }

}