package com.lesa.network

import MockInterceptor
import com.lesa.network.models.JobResponseDTO
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface Api {
    @GET("mock/jobs")
    suspend fun getJobResponse(): JobResponseDTO
}

fun createApi(mockJsonFileName: String = "mock_jobs.json"): Api {
    val json = Json { ignoreUnknownKeys = true }

    return retrofit(
        json = json,
        mockJsonFileName = mockJsonFileName
    ).create()
}

private fun retrofit(
    json: Json,
    mockJsonFileName: String
): Retrofit {
    val jsonConverter: Converter.Factory = json.asConverterFactory("application/json".toMediaType())

    val mockInterceptor = MockInterceptor(mockJsonFileName)

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(mockInterceptor)
        .build()

    return Retrofit.Builder()
        .baseUrl("https://mock.api/")
        .client(okHttpClient)
        .addConverterFactory(jsonConverter)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .build()
}
