package com.bemobile.jaume.challenge.view.product.list

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bemobile.jaume.challenge.R
import com.bemobile.jaume.challenge.extension.d
import com.bemobile.jaume.challenge.view.common.BaseFragmentMainActivity
import com.bemobile.jaume.challenge.view.loadProductDetailFragment
import com.bemobile.jaume.challenge.view.product.adapter.ProductListAdapter
import com.bemobile.jaume.challenge.viewmodel.ProductViewModel
import com.bemobile.jaume.challenge.viewmodel.data.Product
import kotlinx.android.synthetic.main.fragment_list.*

class ProductListFragment : BaseFragmentMainActivity() {

    private lateinit var productAdapter: ProductListAdapter
    private var productViewModel: ProductViewModel? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initProductList()

        initViewModel()
    }

    private fun initProductList() {
        productAdapter = ProductListAdapter().apply {
            onProductClick = {
                d("Product clicked: " + it.sku)
                mainActivity.loadProductDetailFragment(it.sku)
            }
        }

        itemList.apply {
            adapter = productAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun initViewModel() {
        productViewModel = ProductViewModel.getInstance(mainActivity)

        initObservers()

        productViewModel?.loadProducts()
    }

    private fun initObservers() {
        productViewModel?.isDataLoading()?.observe(this, Observer {
            it?.let {
                mainActivity.showProgressBar(it)
            }
        })

        productViewModel?.getProducts()?.observe(this, Observer {
            it?.let {
                d("Number of products loaded: " + it.size)
                showProducts(it)
            }
        })

        productViewModel?.isNetworkError()?.observe(this, Observer {
            it?.let {
                mainActivity.showMessage(getString(R.string.network_error))
            }
        })
    }

    private fun showProducts(products: List<Product>) {
        productAdapter.setItems(products)
    }
}
