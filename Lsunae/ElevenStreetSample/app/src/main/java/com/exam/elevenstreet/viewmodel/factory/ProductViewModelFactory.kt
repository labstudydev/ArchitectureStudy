package com.exam.elevenstreet.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exam.elevenstreet.RetrofitInstance
import com.exam.elevenstreet.data.repository.ProductRepositoryImpl
import com.exam.elevenstreet.data.source.local.ProductLocalDataSourceImpl
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSourceImpl
import com.exam.elevenstreet.viewmodel.ProductViewModel

class ProductViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        ProductViewModel(ProductRepositoryImpl(
            ProductRemoteDataSourceImpl.getInstance(
                RetrofitInstance.getInstance("https://openapi.11st.co.kr/openapi/")
            ),
            ProductLocalDataSourceImpl.getInstance())
        ) as T
}