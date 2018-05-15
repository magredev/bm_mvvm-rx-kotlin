package com.bemobile.jaume.challenge.extension

import java.math.BigDecimal

fun String.toBigDecimal() : BigDecimal =
        BigDecimal(this).setScale(2, BigDecimal.ROUND_HALF_EVEN)

fun BigDecimal.round(scale: Int = 2) : BigDecimal =
        this.setScale(scale, BigDecimal.ROUND_HALF_EVEN)
