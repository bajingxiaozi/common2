package com.xyf.common.util

import com.google.common.base.Preconditions
import com.xyf.common.annotation.WorkThread
import org.apache.commons.lang3.SystemUtils
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

object ProcessUtils {

    private const val TAG = "ProcessUtils"

    @Throws(Exception::class)
    fun killWindowsProcess(name: String) {
        Preconditions.checkArgument(SystemUtils.IS_OS_WINDOWS)
        execute("taskkill", "/im", name, "/f")
    }

    @WorkThread
    @Throws(Exception::class)
    fun execute(parameters: List<String>): List<String> {
        val messages: MutableList<String> = mutableListOf()
        execute(parameters, object : ProcessListener {
            override fun onProgress(message: String) {
                messages.add(message)
            }
        })
        return messages
    }

    @WorkThread
    @Throws(Exception::class)
    fun execute(vararg parameters: String): List<String> {
        return execute(listOf(*parameters))
    }

    interface ProcessListener {
        fun onProgress(message: String)
    }

    @WorkThread
    @Throws(Exception::class)
    fun execute(parameters: List<String>, processListener: ProcessListener) {
        Lg.d(TAG, parameters)
        val processBuilder = ProcessBuilder(parameters).redirectErrorStream(true)
        val executor = processBuilder.start()
        BufferedReader(InputStreamReader(executor.inputStream, StandardCharsets.UTF_8)).use { reader ->
            while (true) {
                val message = reader.readLine() ?: break
                Lg.d(TAG, message)
                processListener.onProgress(message)
            }
        }
        val exitValue = executor.waitFor()
        Lg.d(TAG, parameters, exitValue)
        if (exitValue != NORMAL_EXIT) {
            throw IOException("exit abnormal")
        }
    }

    private const val NORMAL_EXIT = 0

}