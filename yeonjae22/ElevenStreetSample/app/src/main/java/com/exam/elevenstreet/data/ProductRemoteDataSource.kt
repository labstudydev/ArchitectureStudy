package com.exam.elevenstreet.data

import android.content.Context
import com.ElevenStreetApi
import com.exam.RetrofitInstance
import com.exam.elevenstreet.ProductActivity.Companion.API_CODE
import com.exam.elevenstreet.R
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler
import retrofit2.Callback
import java.net.URL

class ProductRemoteDataSource() {
    private var elevenStreetApi: ElevenStreetApi? = null

}