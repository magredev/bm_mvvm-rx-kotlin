package com.bemobile.jaume.challenge.view.product.adapter

import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.bemobile.jaume.challenge.R
import com.bemobile.jaume.challenge.viewmodel.data.Transaction
import kotlinx.android.synthetic.main.item_transaction.view.*

class TransactionItemView constructor(
        context: Context) : RelativeLayout(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.item_transaction, this, true)
    }

    fun bind(transaction: Transaction) {
        transactionAmount.text = transaction.amount.toString()
        transactionCurrency.text = transaction.currency
    }
}
