package com.exam.elevenstreet.view.product

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.factory.ProductViewModelFactory
import com.exam.elevenstreet.R
import com.exam.elevenstreet.databinding.ActivityProductBinding
import com.exam.elevenstreet.view.base.BaseActivity
import com.exam.elevenstreet.viewmodel.ProductViewModel
import com.example.elevenstreet.ProductResponse

class ProductActivity : BaseActivity<ActivityProductBinding>(R.layout.activity_product) {
    private val adapter = ProductAdapter()
    private val viewModel by lazy {
        ViewModelProviders.of(this@ProductActivity, ProductViewModelFactory())
            .get(ProductViewModel::class.java)
    }

    //savedInstanceState 생명주기 관리
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupView()
        setupViewModel()

        binding.btnSearch.setOnClickListener {
            viewModel.searchByKeyword("${binding.edtSearch.text}")
        }

        adapter.setOnClickListener(object : ProductAdapter.OnClickListener {
            override fun onClick(productResponse: ProductResponse) {
                supportFragmentManager.beginTransaction().replace(
                    R.id.frame,
                    ProductFragment.newInstance(
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
        viewModel.productItemList.observe(this, Observer {
            adapter.addData(it)
        })
//        viewModel.productItemList.addOnPropertyChangedCallback(object :
//            Observable.OnPropertyChangedCallback() {
//            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
//                (sender as? ObservableField<List<ProductResponse>>)?.get()?.let {
//                    adapter.addData(it)
//                }
//            }
//        })
    }

    companion object {
        const val API_CODE = "ProductSearch"
    }
}