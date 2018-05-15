package com.bemobile.jaume.challenge.view.product.adapter

import android.view.ViewGroup
import com.bemobile.jaume.challenge.view.common.RecyclerViewAdapterBase
import com.bemobile.jaume.challenge.view.common.ViewWrapper
import com.bemobile.jaume.challenge.viewmodel.data.Product

class ProductListAdapter : RecyclerViewAdapterBase<Product, ProductItemView>() {

    var onProductClick: ((product: Product) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): ProductItemView =
            ProductItemView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<ProductItemView>, position: Int) {
        val product = items[position]

        holder.view.apply {
            bind(product)
        }

        holder.view.setOnClickListener {
            onProductClick?.invoke(product)
        }
    }
}
