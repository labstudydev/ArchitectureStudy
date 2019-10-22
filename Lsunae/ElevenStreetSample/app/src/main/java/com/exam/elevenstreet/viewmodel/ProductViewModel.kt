package com.exam.elevenstreet.viewmodel

import androidx.databinding.ObservableField
import com.exam.elevenstreet.data.model.ProductItem
import com.exam.elevenstreet.data.repository.ProductRepository

class ProductViewModel(private val productRepository: ProductRepository){

   // ProductPresenter 에 있는 소스를 가져와서 넣으면 됨

    val productItemList = ObservableField<List<ProductItem>>()  //ObservabledField를 사용하면 감지를 할 수 있음



}