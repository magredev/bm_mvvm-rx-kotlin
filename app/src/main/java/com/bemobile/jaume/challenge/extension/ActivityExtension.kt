package com.bemobile.jaume.challenge.extension

import android.app.Activity
import android.os.Handler

fun Activity.delay(delay: Long, body: () -> Unit) {
    Handler().postDelayed({
        body.invoke()
    }, delay)
}
