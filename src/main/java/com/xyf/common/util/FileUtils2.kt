package com.xyf.common.util

import com.google.common.base.Preconditions
import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOCase
import org.apache.commons.io.filefilter.DirectoryFileFilter
import org.apache.commons.io.filefilter.SuffixFileFilter
import java.io.*
import java.nio.charset.StandardCharsets
import java.util.*

object FileUtils2 {

    private const val TAG = "FileUtils2"

    fun isFile(file: File): Boolean {
        return file.exists() && file.isFile
    }

    fun isDirectory(directory: File): Boolean {
        return directory.exists() && directory.isDirectory
    }

    fun deleteFile(file: File) {
        if (!file.exists()) {
            Lg.w(TAG, file, "not exist")
            return
        }
        if (file.isFile) {
            val result = file.delete()
            Lg.d(TAG, file, result)
        } else {
            Lg.w(TAG, "not a file", file)
        }
    }

    @Throws(IOException::class)
    fun toReader(file: File): Reader {
        return BufferedReader(InputStreamReader(FileUtils.openInputStream(file), StandardCharsets.UTF_8))
    }

    @Throws(IOException::class)
    fun toWriter(file: File): Writer {
        return BufferedWriter(OutputStreamWriter(FileUtils.openOutputStream(file), StandardCharsets.UTF_8))
    }

    fun randomName(): String {
        return UUID.randomUUID().toString()
    }

    fun randomFile(directory: File): File {
        return File(directory, randomName())
    }

    fun randomDirectory(directory: File): File {
        return randomFile(directory)
    }

}