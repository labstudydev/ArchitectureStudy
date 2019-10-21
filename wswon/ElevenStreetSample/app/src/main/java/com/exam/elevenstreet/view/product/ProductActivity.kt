package com.exam.elevenstreet.view.product

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.model.ProductItem
import com.exam.elevenstreet.data.repository.ProductRepositoryImpl
import com.exam.elevenstreet.data.source.local.ProductLocalDataSourceImpl
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSourceImpl
import com.exam.elevenstreet.databinding.ActivityProductBinding
import com.exam.elevenstreet.network.RetrofitInstance
import com.exam.elevenstreet.view.product.adapter.ProductAdapter
import com.exam.elevenstreet.viewmodel.ProductViewModel


class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding

    private val productViewModel by lazy {
        ProductViewModel(
            ProductRepositoryImpl.getInstance(
                ProductRemoteDataSourceImpl.getInstance(
                    RetrofitInstance.getInstance(CALL_URL)
                ),
                ProductLocalDataSourceImpl.getInstance()
            )
        )
    }

    private val productAdapter by lazy {
        ProductAdapter().apply {
            setOnClickListener(object : ProductAdapter.OnClickListener {
                override fun onClick(productItem: ProductItem) {

                }
            })
        }
    }

    private var isShowingMessage = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product)

        setupView()
        setupViewModel()
    }

    private fun setupView() {
        binding.run {
            rvProduct.run {
                layoutManager = LinearLayoutManager(this@ProductActivity)
                adapter = productAdapter

                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)

                        if (!recyclerView.canScrollVertically(1)) {
                            productViewModel.loadNextProduct(productAdapter.itemCount)
                        }
                    }
                })
            }
            btnSearch.setOnClickListener {
                productAdapter.clearListData()
                productViewModel.searchByKeyword("${edtInputKeyword.text}")
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun setupViewModel() {
        productViewModel.run {
            productItem.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    (sender as? ObservableField<ProductItem>)
                        ?.get()
                        ?.let { productItem ->
                            runOnUiThread {
                                productAdapter.addData(productItem)
                                binding.tvProductCount.text = "${productAdapter.itemCount} 개"
                            }
                        }
                }
            })
            exceptionMessage.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    (sender as? ObservableField<String>)
                        ?.get()
                        ?.let { message ->
                            showMessage(message)
                        }
                }
            })
            showProgressFlag.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    (sender as? ObservableField<Boolean>)
                        ?.get()
                        ?.let { progressFlag ->
                            if (progressFlag) {
                                showLoadingProgress()
                            } else {
                                endDataLoad()
                            }
                        }
                }
            })
        }
    }

    private fun showMessage(message: String) {
        if (!isShowingMessage) {
            isShowingMessage = true
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            Handler().postDelayed({
                isShowingMessage = false
            }, 1000)
        }
    }

    private fun showLoadingProgress() {
        runOnUiThread {
            binding.pgrProductLoad.run {
                visibility = View.VISIBLE
                isIndeterminate = true
            }
        }
    }

    private fun endDataLoad() {
        runOnUiThread {
            binding.pgrProductLoad.run {
                visibility = View.GONE
                isIndeterminate = false
            }
            productViewModel.checkProductEnd(productAdapter.itemCount)
        }
    }


    companion object {
        private const val CALL_URL = "https://openapi.11st.co.kr/openapi/"
        private const val TAG = "ProductActivity"
    }
}
