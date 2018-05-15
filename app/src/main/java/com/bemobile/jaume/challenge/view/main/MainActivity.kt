package com.bemobile.jaume.challenge.view.main

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.bemobile.jaume.challenge.R
import com.bemobile.jaume.challenge.extension.visible
import com.bemobile.jaume.challenge.view.loadProductListFragment
import com.bemobile.jaume.challenge.view.product.detail.ProductDetailFragment
import com.bemobile.jaume.challenge.view.product.list.ProductListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var currentFragment: Fragment? = null

    val PRODUCT_SKU_ARG = "sku"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadProductListFragment()
    }

    fun showMessage(message: String) =
            Snackbar.make(mainRootView, message, Snackbar.LENGTH_SHORT).show()

    fun showProgressBar(isVisible: Boolean) {
        progressBar.visible(isVisible)
    }

    fun setActionBar() {
        when (currentFragment) {
            is ProductListFragment -> {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                supportActionBar?.setTitle(getString(R.string.actionbar_product_list))
            }

            is ProductDetailFragment -> {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setTitle(getString(R.string.actionbar_product_detail))
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
