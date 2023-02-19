package com.example.composeddoggos.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path


interface DoggosAPI {
    @GET("breed/{breed}/images")
    suspend fun getPhotoURLs(
        @Path("breed") symbol: String,
    ): DoggoURLsDto


    @GET("breeds/list/all")
    suspend fun getBreeds(): ResponseBody




    companion object {
        const val BASE_URL = "https://dog.ceo/api/"
    }
}
