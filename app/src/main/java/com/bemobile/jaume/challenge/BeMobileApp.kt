package com.bemobile.jaume.challenge

import android.app.Application
import com.bemobile.jaume.challenge.repository.ProductRepository
import com.bemobile.jaume.challenge.repository.api.TradeService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BeMobileApp : Application() {

    // todo: improve it using Dagger2
    companion object {
        private lateinit var retrofit: Retrofit
        private lateinit var api: TradeService
        private lateinit var productRepository: ProductRepository

        fun injectProductRepository() = productRepository
    }

    override fun onCreate() {
        super.onCreate()

        retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .build()

        api = retrofit.create(TradeService::class.java)

        productRepository = ProductRepository(api)
    }
}
