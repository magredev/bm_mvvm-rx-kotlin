package com.bemobile.jaume.challenge.repository.api

import com.bemobile.jaume.challenge.extension.toBigDecimal
import com.bemobile.jaume.challenge.viewmodel.data.Transaction
import com.google.gson.annotations.SerializedName

data class TransactionResponse(

        @SerializedName("sku")
        val sku: String,

        @SerializedName("amount")
        val amount: String,

        @SerializedName("currency")
        val currency: String
) {
        fun toTransaction() =
                Transaction(this.sku, this.amount.toBigDecimal(), this.currency)
}
