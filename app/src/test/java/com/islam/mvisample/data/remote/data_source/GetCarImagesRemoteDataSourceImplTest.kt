package com.islam.mvisample.data.remote.data_source

import com.islam.mvisample.data.remote.NetworkResponse
import com.islam.mvisample.data.remote.api.ApiService
import com.islam.mvisample.domain.entites.CarResponse
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import retrofit2.Response

class GetCarImagesRemoteDataSourceImplTest {

    private lateinit var dataSource: GetCarImagesRemoteDataSourceImpl

    @Mock
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        dataSource = GetCarImagesRemoteDataSourceImpl(apiService)
    }

    @Test
    fun `test get cars in success statue`() = runBlocking {
        val params = "111"
        val response = Response.success(CarResponse(listOf()))
        val networkResponse = NetworkResponse.Success(response)

        whenever(apiService.getCars(params)).thenReturn(response)

        TestCase.assertEquals(networkResponse, dataSource.getCars(params))
    }
}