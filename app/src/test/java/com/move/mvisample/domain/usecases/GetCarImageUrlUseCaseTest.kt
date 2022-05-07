package com.move.mvisample.domain.usecases

import com.move.mvisample.domain.entites.CarImage
import com.move.mvisample.domain.entites.CarResponse
import com.move.mvisample.domain.repositories.GetCarImagesRepository
import com.move.mvisample.presentation.viewmodel.main.MainResults
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import retrofit2.Response

class GetCarImageUrlUseCaseTest {

    private lateinit var useCase: GetCarImageUrlUseCase

    @Mock
    private lateinit var repository: GetCarImagesRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = GetCarImageUrlUseCase(repository)
    }

    @Test
    fun testReturnImageUrlCorrectly() = runBlocking {

        val id = "111"
        val url = "m.mobile.de/yams-proxy/img.classistatic.de/api/v1/mo-prod/images/78/"
        val imageList = listOf(CarImage(url))

        whenever(repository.getCars(id)).thenReturn(Response.success(CarResponse(imageList)))

        val expectedUrl = "https://img.classistatic.de/api/v1/mo-prod/images/78/?rule=mo-640.jpg"
        val expectedImageList = listOf(CarImage(expectedUrl))

        val actual = useCase.execute(id)
        val expected = MainResults.ImageURL(expectedImageList)

        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testReturnEmptyList() = runBlocking {

        val id = "111"
        val imageList = listOf<CarImage>()

        whenever(repository.getCars(id)).thenReturn(Response.success(CarResponse(imageList)))

        val actual = useCase.execute(id)
        val expected = MainResults.EmptyList

        Assert.assertEquals(actual, expected)
    }

    @Test
    fun testFailureResponse() = runBlocking {

        val id = "111"
        val errorResponse =
            "{\n\"type\": \"error\",\n\"message\": \"error\"}"
        val errorResponseBody = errorResponse.toResponseBody("application/json".toMediaTypeOrNull())
        val response = Response.error<CarResponse>(501, errorResponseBody)

        whenever(repository.getCars(id)).thenReturn(response)

        val actual = useCase.execute(id)
        val expected = MainResults.ERROR(response.message(), response.code().toLong())

        Assert.assertEquals(actual, expected)
    }

}