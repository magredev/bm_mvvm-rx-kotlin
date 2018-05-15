package com.bemobile.jaume.challenge.repository.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface TradeService {

    @Headers("Accept: application/json")
    @GET("rates")
    fun getRates(): Single<List<RateResponse>>

    @Headers("Accept: application/json")
    @GET("transactions")
    fun getTransactions(): Single<List<TransactionResponse>>
}
