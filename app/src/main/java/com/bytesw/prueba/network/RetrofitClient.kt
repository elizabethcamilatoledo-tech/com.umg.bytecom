package com.bytesw.prueba.network

import com.bytesw.prueba.models.Donut
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("donuts")
    fun getDonuts(): Call<List<Donut>>
}

object RetrofitClient {
    private const val BASE_URL = "https://private-1ca53c-training45.apiary-mock.com/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
