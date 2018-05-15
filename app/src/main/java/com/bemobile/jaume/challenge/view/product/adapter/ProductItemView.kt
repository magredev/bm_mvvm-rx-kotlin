package com.bemobile.jaume.challenge.view.product.adapter

import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.bemobile.jaume.challenge.R
import com.bemobile.jaume.challenge.viewmodel.data.Product
import kotlinx.android.synthetic.main.item_product.view.*

class ProductItemView constructor(
        context: Context) : RelativeLayout(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.item_product, this, true)
    }

    fun bind(product: Product) {
        productName.text = product.sku
    }
}
