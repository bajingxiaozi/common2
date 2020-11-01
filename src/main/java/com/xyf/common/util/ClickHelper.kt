package com.xyf.common.util

import com.xyf.common.annotation.UiThread
import kotlin.math.abs

object ClickHelper {

    private const val CLICK_INTERVAL: Long = 100
    private var lastClickTime: Long = 0

    @UiThread
    fun canClick(): Boolean {
        return if (abs(System.currentTimeMillis() - lastClickTime) > CLICK_INTERVAL) {
            lastClickTime = System.currentTimeMillis()
            true
        } else {
            false
        }
    }
}