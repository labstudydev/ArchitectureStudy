package com.exam.elevenstreet.view.product

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.repository.ProductRepositoryImpl
import com.exam.elevenstreet.data.source.local.ProductLocalDataSourceImpl
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSourceImpl
import com.exam.elevenstreet.databinding.ActivityProductBinding
import com.exam.elevenstreet.network.RetrofitInstance
import com.exam.elevenstreet.viewmodel.ProductViewModel
import com.example.elevenstreet.ProductResponse

class ProductActivity : AppCompatActivity() {
    val productFragment = ProductFragment()
    private val adapter = ProductAdapter()
    private lateinit var binding: ActivityProductBinding
    private val viewModel = ProductViewModel(
        ProductRepositoryImpl.getInstance(
            ProductRemoteDataSourceImpl.getInstance
                (RetrofitInstance.getInstance("https://openapi.11st.co.kr/openapi/")),
            ProductLocalDataSourceImpl.getInstance()
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product)

        setupView()
        setupViewModel()

        binding.btnSearch.setOnClickListener {
            viewModel.searchByKeyword("${binding.edtSearch.text}")
        }

        adapter.setOnClickListener(object : ProductAdapter.OnClickListener {
            override fun onClick(productResponse: ProductResponse) {
                supportFragmentManager.beginTransaction().replace(
                    R.id.frame,
                    productFragment.newInstance(
                        productResponse.productName,
                        productResponse.productPrice,
                        productResponse.productSeller,
                        productResponse.productDelivery
                    )
                ).addToBackStack(null).commit()
            }
        })
    }

    private fun setupView() {
        val manager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel.productItemList.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                (sender as? ObservableField<List<ProductResponse>>)?.get()?.let {
                    adapter.addData(it)
                }
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.beginTransaction().remove(productFragment).commit()
        supportFragmentManager.popBackStack()
    }

    companion object {
        const val API_CODE = "ProductSearch"
    }
}