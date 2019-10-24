package com.exam.elevenstreet.view.product

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.RetrofitInstance
import com.exam.elevenstreet.view.product.adapter.ProductAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.repository.ProductRepositoryImpl
import com.exam.elevenstreet.data.source.local.ProductLocalDataSourceImpl
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSourceImpl
import com.exam.elevenstreet.databinding.ActivityMainBinding
import com.exam.elevenstreet.network.model.ProductResponse
import com.exam.elevenstreet.view.product.presenter.ProductContract
import com.exam.elevenstreet.viewmodel.ProductViewModel

class ProductActivity : AppCompatActivity() {

    private lateinit var productAdapter: ProductAdapter
    private lateinit var activityMainBinding: ActivityMainBinding

    private val productViewModel = ProductViewModel(
        ProductRepositoryImpl(
            ProductRemoteDataSourceImpl.getInstance(
                RetrofitInstance.getInstance("https://openapi.11st.co.kr/openapi/")),
            ProductLocalDataSourceImpl.getInstance()
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        productAdapter = ProductAdapter(mutableListOf())

        productAdapter.setOnClickListener(object : ProductAdapter.OnClickListener {
            override fun onClick(productItem: ProductResponse) {

            }
        })
        
        setupView()
        setupViewModel()
    }

    private fun setupView() {
        val layoutManager = LinearLayoutManager(this@ProductActivity)

        activityMainBinding.recyclerViewProduct.run {
            this.layoutManager = layoutManager
            adapter = productAdapter
        }

        activityMainBinding.btnSearch.setOnClickListener {
            productViewModel.searchByKeyword("${activityMainBinding.editSearch.text}")
        }
    }

    private fun setupViewModel() {
        productViewModel.productItemList.addOnPropertyChangedCallback(object :

        Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                (sender as? ObservableField<List<ProductResponse>>)
                    ?.get()
                    ?.let { productList ->
                        productAdapter.replaceAll(productList)
                    }
            }
        })
    }

    companion object {
        private const val TAG = "ProductActivity"
        const val API_CODE = "ProductSearch"
    }
}