package com.bemobile.jaume.challenge.view.product.adapter

import android.view.ViewGroup
import com.bemobile.jaume.challenge.view.common.RecyclerViewAdapterBase
import com.bemobile.jaume.challenge.view.common.ViewWrapper
import com.bemobile.jaume.challenge.viewmodel.data.Transaction

class TransactionListAdapter : RecyclerViewAdapterBase<Transaction, TransactionItemView>() {

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): TransactionItemView =
            TransactionItemView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<TransactionItemView>, position: Int) {
        val transaction = items[position]

        holder.view.apply {
            bind(transaction)
        }
    }
}