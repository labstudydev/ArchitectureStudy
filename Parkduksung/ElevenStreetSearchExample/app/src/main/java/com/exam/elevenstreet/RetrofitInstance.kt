package com.exam.elevenstreet

import retrofit2.Retrofit

object RetrofitInstance {
    inline fun <reified T> getInstance(url: String): T? {
        return Retrofit.Builder()
            .baseUrl(url)
            .build().create(T::class.java)
    }
}

