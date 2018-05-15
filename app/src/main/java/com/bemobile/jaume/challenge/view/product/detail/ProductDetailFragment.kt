package com.bemobile.jaume.challenge.view.product.detail

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bemobile.jaume.challenge.R
import com.bemobile.jaume.challenge.extension.d
import com.bemobile.jaume.challenge.view.common.BaseFragmentMainActivity
import com.bemobile.jaume.challenge.view.product.adapter.TransactionListAdapter
import com.bemobile.jaume.challenge.viewmodel.ProductViewModel
import com.bemobile.jaume.challenge.viewmodel.data.Transaction
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_product_detail.*

class ProductDetailFragment : BaseFragmentMainActivity() {

    private var skuArg: String? = null

    private lateinit var transactionAdapter: TransactionListAdapter
    private var productViewModel: ProductViewModel? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_product_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        skuArg = arguments?.getString(mainActivity.PRODUCT_SKU_ARG)

        skuArg?.let {
            initViews(it)

            initViewModel(it)
        }
    }

    private fun initViews(sku: String) {
        productSku.text = sku

        transactionAdapter = TransactionListAdapter()

        itemList.apply {
            adapter = transactionAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun initViewModel(sku: String) {
        productViewModel = ProductViewModel.getInstance(mainActivity)

        productViewModel?.getTransactions(sku)?.let {
            d("Transactions size: " + it.size)
            showTransactions(it)
        }

        productViewModel?.getEurAmount(sku)?.let {
            productTotalAmount.text = String.format(getString(R.string.total_sum), it.toString())
        }
    }

    private fun showTransactions(transactions: List<Transaction>) {
        transactionAdapter.setItems(transactions)
    }
}
