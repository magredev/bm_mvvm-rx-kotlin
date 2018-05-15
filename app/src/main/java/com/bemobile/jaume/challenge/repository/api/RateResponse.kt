package com.bemobile.jaume.challenge.repository.api

import com.bemobile.jaume.challenge.viewmodel.data.Rate
import com.google.gson.annotations.SerializedName

data class RateResponse(

        @SerializedName("from")
        val from: String,

        @SerializedName("to")
        val to: String,

        @SerializedName("rate")
        val rate: String
) {
        fun toRate() : Rate =
                Rate(this.from, this.to, this.rate.toDouble())
}
