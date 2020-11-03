package com.xyf.commonfx

import javafx.scene.image.Image
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL

object R {

    fun getLayout(name: String): URL {
        return R::class.java.classLoader.getResource("layout/$name")
    }

    fun getDrawable(name: String): InputStream {
        return getResource("drawable", name)
    }

    @Throws(IOException::class)
    fun getImage(name: String): Image {
        getDrawable(name).use { inputStream -> return Image(inputStream) }
    }

    fun getResource(type: String, name: String): InputStream {
        return R::class.java.classLoader.getResourceAsStream("$type/$name")
    }

    fun getXml(name: String): InputStream {
        return getResource("xml", name)
    }

    @Throws(IOException::class)
    fun getImage(file: File): Image {
        FileInputStream(file).use { inputStream -> return Image(inputStream) }
    }

}