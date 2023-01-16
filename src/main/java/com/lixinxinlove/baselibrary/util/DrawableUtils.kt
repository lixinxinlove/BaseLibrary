package com.lixinxinlove.baselibrary.util

import android.graphics.drawable.Drawable
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PixelFormat

object DrawableUtils {
    fun drawableToBitmap(drawable: Drawable): Bitmap? {
        //声明将要创建的bitmap
        var bitmap: Bitmap? = null
        //获取图片宽度
        val width = drawable.intrinsicWidth
        //获取图片高度
        val height = drawable.intrinsicHeight
        //图片位深，PixelFormat.OPAQUE代表没有透明度，RGB_565就是没有透明度的位深，否则就用ARGB_8888。详细见下面图片编码知识。
        val config =
            if (drawable.opacity != PixelFormat.OPAQUE) Bitmap.Config.ARGB_8888 else Bitmap.Config.RGB_565
        //创建一个空的Bitmap
        bitmap = Bitmap.createBitmap(width, height, config)
        //在bitmap上创建一个画布
        val canvas = Canvas(bitmap)
        //设置画布的范围   
        drawable.setBounds(0, 0, width, height)
        //将drawable绘制在canvas上
        drawable.draw(canvas)
        return bitmap
    }
}