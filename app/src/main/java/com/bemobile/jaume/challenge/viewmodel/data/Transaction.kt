package com.bemobile.jaume.challenge.viewmodel.data

import java.math.BigDecimal

data class Transaction(
        val sku: String,
        val amount: BigDecimal,
        val currency: String
)
