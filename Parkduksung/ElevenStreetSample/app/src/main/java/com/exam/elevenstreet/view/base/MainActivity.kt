package com.exam.elevenstreet.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.exam.elevenstreet.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//
    }

    companion object {
        private const val TAG = "MainActivity"
//TODO : 검색영역 작업하는데 사용할 변수
//        const val API_CODE = "ProductSearch"
    }
}
