package com.bemobile.jaume.challenge.view.common

import android.support.v4.app.Fragment
import com.bemobile.jaume.challenge.view.main.MainActivity

open class BaseFragmentMainActivity : Fragment() {

    val mainActivity: MainActivity
        get() = activity as MainActivity

    override fun onResume() {
        super.onResume()
        mainActivity.currentFragment = this
        mainActivity.setActionBar()
    }

    override fun onStop() {
        super.onStop()
        mainActivity.currentFragment = null
    }
}
