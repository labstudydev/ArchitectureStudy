package com.exam.elevenstreet

import android.app.Application
import android.content.Context

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun context(): Context = applicationContext

    companion object {
        lateinit var instance: MyApplication
            private set
    }
}