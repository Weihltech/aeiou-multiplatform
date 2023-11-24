package org.wells.aeiou.screenadaptor

import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Des:
 * 参考尺寸：
 * 分辨率(px) = 320 * 160 , dpi = 160 ，density = 1 , 分辨率(dp) = 320 * 160
 *
 * dp           ,密度无关像素的度量值
 * dpi          ,每英寸包含多少像素点（densityDpi）
 * density      ,密度 [dp 与 px 之间的换算关系]
 * px           ,像素点
 * scaleDensity ,字体放大因子
 *
 * ------- 参考 TypedValue.applyDimension()------
 * 1、sp = value * metrics.scaledDensity
 * 2、px = density * dp
 * 3、densityDpi = density * 160
 * 4、scaleDensity / density  = targetScaleDensity / targetDensity
 *
 * @author Weihl
 * Created 2022/2/19
 */

class UiScreen(val width: Int, val height: Int, val dpi: Int) {

    init {
        when {
            width <= 0 -> throw RuntimeException("error width , ScreenAdaptor")
            height <= 0 -> throw RuntimeException("error height , ScreenAdaptor")
            dpi <= 0 -> throw RuntimeException("error dpi , ScreenAdaptor")
        }
    }

    val density by lazy { dpi.toFloat() / 160 }

    val densityDpi = dpi

    val scaleDensity = density

    val dpWidth by lazy { width / density }

    val dpHeight by lazy { height / density }

    val inch by lazy {
        (sqrt(width.toDouble().pow(2.0) + height.toDouble().pow(2.0)) / dpi).toFloat()
    }

    // px = density * dp
    fun toDensity(width: Int): Float = width / dpWidth

    // densityDpi = density * 160
    fun toDensityDpi(density: Float): Int = (density * 160).toInt()

    // scaleDensity / density  = targetScaleDensity / targetDensity
    fun toScaleDensity(scaledDensity: Float, density: Float, tDensity: Float): Float =
        (scaledDensity / density) * tDensity

    override fun toString(): String {
        return "UiScreen(width=$width, height=$height, dpi=$dpi, inch=$inch)"
    }
}

