package com.bemobile.jaume.challenge.repository

import com.bemobile.jaume.challenge.extension.d
import com.bemobile.jaume.challenge.repository.api.TradeService
import com.bemobile.jaume.challenge.viewmodel.data.Rate
import com.bemobile.jaume.challenge.viewmodel.data.Transaction
import io.reactivex.Single

class ProductRepository(val api: TradeService) {

    fun getRates(): Single<List<Rate>> =
            api.getRates()
                .flatMap { rateResponseList ->
                    d("Getting rates from service...")
                    var result = emptyList<Rate>()
                    for (item in rateResponseList) {
                        result = result.plus(item.toRate())
                    }
                    Single.just(result)
                }

    fun getTransactions(): Single<List<Transaction>> =
            api.getTransactions()
                    .flatMap { transactionResponseList ->
                        d("Getting transactions from service...")
                        var result = emptyList<Transaction>()
                        for (item in transactionResponseList) {
                            result = result.plus(item.toTransaction())
                        }
                        Single.just(result)
                    }
}
