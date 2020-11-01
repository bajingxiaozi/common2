package com.xyf.common.util

import java.util.prefs.Preferences

class Sp(clazz: Class<*>) {

    private val sp: Preferences = Preferences.userNodeForPackage(clazz)
    operator fun set(key: String, value: String) {
        sp.put(key, value)
    }

    @JvmOverloads
    operator fun get(key: String, def: String? = null): String? {
        return sp[key, def]
    }

    fun remove(key: String) {
        sp.remove(key)
    }

}