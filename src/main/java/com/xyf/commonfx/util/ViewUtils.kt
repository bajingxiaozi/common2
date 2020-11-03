package com.xyf.commonfx.util

import javafx.scene.input.Clipboard
import javafx.scene.input.ClipboardContent
import javafx.stage.Window

object ViewUtils {

    private const val TAG = "ViewUtils"

    private lateinit var window: Window

    fun init(window: Window) {
        this.window = window
    }

    fun copyToClipboard(string: String) {
        val clipboard = Clipboard.getSystemClipboard()
        val cc = ClipboardContent()
        cc.putString(string)
        clipboard.setContent(cc)
    }

}