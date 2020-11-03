package com.xyf.common.util

import com.xyf.common.annotation.UiThread
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

object Lg {

    private val LOGGER_MAP: MutableMap<String, Logger> = mutableMapOf()

    @UiThread
    private fun getLogger(tag: String): Logger {
        if (!LOGGER_MAP.containsKey(tag)) {
            LOGGER_MAP[tag] = LoggerFactory.getLogger(tag)
        }
        return LOGGER_MAP[tag]!!
    }

    fun e(tag: String, vararg messages: Any?) {
        log(TYPE.ERROR, tag, *messages)
    }

    fun w(tag: String, vararg messages: Any?) {
        log(TYPE.WARN, tag, *messages)
    }

    fun d(tag: String, vararg messages: Any?) {
        log(TYPE.DEBUG, tag, *messages)
    }

    fun i(tag: String, vararg messages: Any?) {
        log(TYPE.INFO, tag, *messages)
    }

    private fun log(type: TYPE, tag: String, vararg objects: Any?) {
        for (obj in objects) {
            when (obj) {
                is Throwable -> {
                    logMessage(type, tag, obj.toString())
                    for (trace in obj.stackTrace) {
                        logMessage(type, tag, trace.toString())
                    }
                }
                is Collection<*> -> {
                    for (item in obj) {
                        logMessage(type, tag, item.toString())
                    }
                }
                else -> {
                    for (s in obj.toString().split("\n").toTypedArray()) {
                        logMessage(type, tag, s)
                    }
                }
            }
        }
    }

    @UiThread
    private fun logMessage(type: TYPE, tag: String, message: String?) {
        when (type) {
            TYPE.ERROR -> getLogger(tag).error(message)
            TYPE.WARN -> getLogger(tag).warn(message)
            TYPE.INFO -> getLogger(tag).info(message)
            else -> getLogger(tag).debug(message)
        }
    }

    private enum class TYPE {
        DEBUG, INFO, WARN, ERROR
    }

}