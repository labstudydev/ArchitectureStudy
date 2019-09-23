package com.exam.elevenstreet.data.Repository

import com.exam.elevenstreet.ext.isConnectedToNetwork
import com.exam.elevenstreet.util.App

class ProductRepository {


    fun ConnectNetwork(): Boolean {
        if (App.instance.context().isConnectedToNetwork())
            return true
        else
            return false
    }
}

