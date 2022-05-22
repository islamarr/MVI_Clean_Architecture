package com.islam.mvisample.data.repositories

import com.islam.mvisample.data.remote.NetworkResponse
import com.islam.mvisample.data.remote.data_source.GetCarImagesRemoteDataSource
import com.islam.mvisample.domain.entites.CarResponse
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import retrofit2.Response

class GetCarImagesRepositoryImplTest {

    private lateinit var repository: GetCarImagesRepositoryImpl

    @Mock
    private lateinit var remoteDataSource: GetCarImagesRemoteDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = GetCarImagesRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `test get cars in success statue`() = runBlocking {
        val params = "111"
        val response = Response.success(CarResponse(listOf()))
        val networkResponse = NetworkResponse.Success(response)

        whenever(remoteDataSource.getCars(params)).thenReturn(networkResponse)

        assertEquals(networkResponse, repository.getCars(params))
    }
}