package com.exam.elevenstreet


import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ElevenStreetApi {
    @GET("OpenApiService.tmall")
    fun getProductList(
        @Query("key") key: String,
        @Query("apiCode") apiCode: String,
        @Query("keyword") keyword: String,
        @Query("pageNum") pageNum: Int
    ): Call<ResponseBody>
}