package com.bemobile.jaume.challenge.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.bemobile.jaume.challenge.BeMobileApp
import com.bemobile.jaume.challenge.extension.d
import com.bemobile.jaume.challenge.extension.round
import com.bemobile.jaume.challenge.viewmodel.data.Product
import com.bemobile.jaume.challenge.viewmodel.data.Rate
import com.bemobile.jaume.challenge.viewmodel.data.Transaction
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.math.BigDecimal

class ProductViewModel : ViewModel() {

    companion object {
        private var INSTANCE : ProductViewModel? = null

        private val productRepository = BeMobileApp.injectProductRepository()

        fun getInstance(activity: FragmentActivity): ProductViewModel? {
            if (INSTANCE == null) {
                INSTANCE = ViewModelProviders.of(activity).get(ProductViewModel::class.java)
            }
            return INSTANCE
        }
    }

    private val EUR = "EUR"

    private var rates: List<Rate>? = null
    private var productTransactions: LinkedHashMap<String, List<Transaction>> = LinkedHashMap()

    private var isDataLoading: MutableLiveData<Boolean> = MutableLiveData()
    private var isNetworkError: MutableLiveData<Boolean> = MutableLiveData()
    private var products: MutableLiveData<List<Product>> = MutableLiveData()

    fun getProducts(): LiveData<List<Product>> = products

    fun isDataLoading() : LiveData<Boolean> = isDataLoading

    fun isNetworkError() : LiveData<Boolean> = isNetworkError

    fun loadProducts() {
        if (products.value == null || products.value!!.isEmpty()) {
            d("Loading products...")

            isDataLoading.postValue(true)

            loadRatesFromRepository()

            loadProductsFromRepository()
        }
    }

    fun getTransactions(productSku: String) : List<Transaction> {
        d("Getting transactions...")
        isDataLoading.postValue(true)
        if (productTransactions.containsKey(productSku)) {
            isDataLoading.postValue(false)
            return productTransactions.get(productSku)!!
        }

        isDataLoading.postValue(false)
        return emptyList()
    }

    fun getEurAmount(sku: String): BigDecimal {
        var totalAmount: BigDecimal = BigDecimal.ZERO

        if (productTransactions.containsKey(sku)) {
            val transactions: List<Transaction> = productTransactions.get(sku)!!
            for (item in transactions) {
                totalAmount = totalAmount.add(convertTransactionAmountToEuro(item))
            }
        }

        return totalAmount.round()
    }

    private fun loadRatesFromRepository() {
        productRepository.getRates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({rateList ->
                    rates = rateList
                },
                { error ->
                    isNetworkError.postValue(true)
                })
    }

    private fun loadProductsFromRepository() {
        productRepository.getTransactions()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({transactionList ->
                    setProductTransactions(transactionList)
                    val list = getProductsFromTransactions()
                    products.postValue(list)
                    isDataLoading.postValue(false)
                },
                { error ->
                    isNetworkError.postValue(true)
                    isDataLoading.postValue(false)
                })
    }

    private fun getProductsFromTransactions() : List<Product> {
        var list: List<Product> = emptyList()
        for (key in productTransactions.keys) {
            list = list.plus(Product(key))
        }
        return list
    }

    private fun setProductTransactions(transactions: List<Transaction>) {
        for (transaction in transactions) {
            if (productTransactions.containsKey(transaction.sku)) {
                var list: List<Transaction> = productTransactions.getValue(transaction.sku)
                list = list.plus(transaction)
                productTransactions.set(transaction.sku, list)
            } else {
                productTransactions.put(transaction.sku, listOf(transaction))
            }
        }
    }

    private fun convertTransactionAmountToEuro(transaction: Transaction): BigDecimal {
        if (transaction.currency == EUR) {
            return transaction.amount
        } else {
            rates?.let {
                for (item in rates!!) {
                    if (item.from == EUR && item.to == transaction.currency) {
                        return BigDecimal(item.rate).multiply(transaction.amount)
                    }
                }

                // todo: the conversion with 1+ steps is missed
            }
        }

        return BigDecimal.ZERO
    }
}
