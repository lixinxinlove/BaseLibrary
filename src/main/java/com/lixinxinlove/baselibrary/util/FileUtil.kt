package com.lixinxinlove.baselibrary.util

import android.content.Context
import android.util.Log
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

object FileUtil {


    fun readAssetsFile(mContext: Context, fileName: String): String {
        val input = mContext.assets.open(fileName) //传入文件名称 读取assets文件

        val bf = BufferedReader(InputStreamReader(input))
        val sb = StringBuilder()
        bf.use {

            it.forEachLine { s ->
                val ss = s.trim()
                if (ss.isNotEmpty()) {
                    sb.append(ss)
                }

                // Log.e("FileUtil", s)
            }
            Log.e("FileUtil", sb.toString())
            // sb.toString()
        }

        return sb.toString()
    }

}