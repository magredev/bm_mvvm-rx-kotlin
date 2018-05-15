package com.bemobile.jaume.challenge.view

import android.content.Intent
import com.bemobile.jaume.challenge.R
import com.bemobile.jaume.challenge.extension.instanceOf
import com.bemobile.jaume.challenge.view.main.MainActivity
import com.bemobile.jaume.challenge.view.product.detail.ProductDetailFragment
import com.bemobile.jaume.challenge.view.product.list.ProductListFragment
import com.bemobile.jaume.challenge.view.splash.SplashActivity

fun SplashActivity.goToMainActivity() =
        startActivity(Intent(this, MainActivity::class.java))

fun MainActivity.loadProductListFragment() =
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ProductListFragment())
                .commit()

fun MainActivity.loadProductDetailFragment(productSku: String) =
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer,
                        instanceOf<ProductDetailFragment>(Pair(PRODUCT_SKU_ARG, productSku)))
                .addToBackStack(ProductDetailFragment::class.java.name)
                .commit()
