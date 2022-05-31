package com.islam.mvisample.domain.usecases

import com.islam.mvisample.data.remote.NetworkResponse
import com.islam.mvisample.domain.entites.CarImage
import com.islam.mvisample.domain.entites.CarResponse
import com.islam.mvisample.domain.repositories.CarImagesRepository
import com.islam.mvisample.presentation.viewmodel.main.MainResults
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import retrofit2.Response

class CarImageUrlUseCaseTest {

    private lateinit var useCase: GetCarImageUrlUseCase

    @Mock
    private lateinit var repository: CarImagesRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = GetCarImageUrlUseCase(repository)
    }

    @Test
    fun `when execute usecase return CarImageURLListLoaded result with updated urls in success response`() =
        runBlocking {

            val id = "111"
            val url = "m.mobile.de/yams-proxy/img.classistatic.de/api/v1/mo-prod/images/78/"
            val imageList = listOf(CarImage(url))

            whenever(repository.getCars(id)).thenReturn(
                NetworkResponse.Success(
                    Response.success(
                        CarResponse(imageList)
                    )
                )
            )

            val expectedUrl =
                "https://img.classistatic.de/api/v1/mo-prod/images/78/?rule=mo-640.jpg"
            val expectedImageList = listOf(CarImage(expectedUrl))

            val actual = useCase.execute(id)
            val expected = MainResults.CarImageURLListLoaded(expectedImageList)

            assertEquals(actual, expected)
        }

    @Test
    fun `when get empty car list response return CarImageURLEmptyList result`() = runBlocking {

        val id = "111"
        val imageList = listOf<CarImage>()

        whenever(repository.getCars(id)).thenReturn(
            NetworkResponse.Success(
                Response.success(
                    CarResponse(imageList)
                )
            )
        )

        val actual = useCase.execute(id)
        val expected = MainResults.CarImageURLEmptyList

        assertEquals(actual, expected)
    }

    @Test
    fun `test failure Response`() = runBlocking {

        val id = "111"
        val response = NetworkResponse.Failure<Response<CarResponse>>(501, "reason")

        whenever(repository.getCars(id)).thenReturn(response)

        val actual = useCase.execute(id)
        val expected = MainResults.Error(response.reason ?: "", response.httpCode)

        assertEquals(actual, expected)
    }

    @Test
    fun `test failure Response with null reason`() = runBlocking {

        val id = "111"
        val response = NetworkResponse.Failure<Response<CarResponse>>(501, null)

        whenever(repository.getCars(id)).thenReturn(response)

        val actual = useCase.execute(id)
        val expected = MainResults.UnExpectedError

        assertEquals(actual, expected)
    }

    @Test
    fun `when get null response return UnExpectedError result`() = runBlocking {

        val id = "111"
        whenever(repository.getCars(id)).thenReturn(NetworkResponse.Success(Response.success(null)))

        val actual = useCase.execute(id)
        val expected = MainResults.UnExpectedError

        assertEquals(actual, expected)
    }

}