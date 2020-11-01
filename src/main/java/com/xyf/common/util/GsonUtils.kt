package com.xyf.common.util

import com.google.gson.GsonBuilder
import com.xyf.common.annotation.WorkThread
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException

object GsonUtils {

    private val TAG = GsonUtils::class.java.simpleName

    @WorkThread
    @Throws(IOException::class)
    fun save(bean: Any, file: File) {
        val tempFile = File(FileUtils.getTempDirectory(), FileUtils2.randomName())
        try {
            FileUtils.forceMkdirParent(tempFile)
            FileUtils2.toWriter(tempFile).use { reader -> GsonBuilder().setPrettyPrinting().create().toJson(bean, reader) }
            FileUtils2.deleteFile(file)
            FileUtils.moveFile(tempFile, file)
            Lg.d(TAG, "save", bean, file)
        } finally {
            FileUtils2.deleteFile(tempFile)
        }
    }

}