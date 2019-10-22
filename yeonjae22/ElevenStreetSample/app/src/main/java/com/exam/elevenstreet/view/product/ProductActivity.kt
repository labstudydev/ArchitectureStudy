package com.exam.elevenstreet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.data.repository.ProductRepositoryImpl
import com.exam.elevenstreet.data.source.local.ProductLocalDataSourceImpl
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSourceImpl
import com.exam.elevenstreet.databinding.ActivityProductBinding
import com.exam.elevenstreet.network.RetrofitInstance
import com.exam.elevenstreet.view.product.ProductFragment
import com.exam.elevenstreet.view.product.presenter.ProductContract
import com.exam.elevenstreet.view.product.presenter.ProductPresenter
import com.example.elevenstreet.ProductResponse

class ProductActivity : AppCompatActivity(), ProductContract.View {
    override lateinit var presenter: ProductContract.Presenter
    private val adapter = ProductAdapter()
    private lateinit var binding: ActivityProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product)

        presenter = ProductPresenter(
            ProductRepositoryImpl.getInstance(
                ProductRemoteDataSourceImpl.getInstance
                    (RetrofitInstance.getInstance("https://openapi.11st.co.kr/openapi/")),
                ProductLocalDataSourceImpl.getInstance()
            ),
            this
        )
        setupView()

        binding.btnSearch.setOnClickListener {
            presenter.searchByKeyword("${binding.edtSearch.text}")
        }

        adapter.setOnClickListener(object : ProductAdapter.OnClickListener {
            override fun onClick(productResponse: ProductResponse) {
                val productFragment = ProductFragment()
                supportFragmentManager.beginTransaction().replace(
                    R.id.frame,
                    productFragment.newInstance(
                        productResponse.productName,
                        productResponse.productPrice,
                        productResponse.productSeller,
                        productResponse.productDelivery
                    )
                ).commit()
            }
        })
    }

    override fun showProductList(productList: List<ProductResponse>) {
        adapter.addData(productList)
    }

    private fun setupView() {
        val manager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = adapter
    }

    fun back(fragment: Fragment) {
        supportFragmentManager.beginTransaction().remove(fragment).commit()
    }

    companion object {
        const val API_CODE = "ProductSearch"
    }
}