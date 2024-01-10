package org.wells.aeiou.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream


/**
 * @desc
 *
 * @author weihl
 * @date 2023/12/18
 */
// -------改变大小----
fun adjustSampleSize(options: BitmapFactory.Options, width: Int, height: Int): Int {
    // Raw height and width of image
    val orgHeight = options.outHeight
    val orgWidth = options.outWidth
    var inSampleSize = 1
    if (orgHeight > height || orgWidth > width) {
        val halfHeight = orgHeight / 2
        val halfWidth = orgWidth / 2
        // 计算最大的 inSampleSize 值，该值是 2 的幂，并保持高度和宽度都大于请求的高度和宽度。
        while (halfHeight / inSampleSize >= height
            && halfWidth / inSampleSize >= width
        ) {
            inSampleSize *= 2
        }
    }
    return inSampleSize
}

fun loadBySampleSize(res: Resources, resid: Int, width: Int, height: Int): Bitmap {
    val options = BitmapFactory.Options().apply {
        // 设置为只获取图片大小
        inJustDecodeBounds = true
        // 获取图片原 Options
        BitmapFactory.decodeResource(res, resid, this)
        // 通过原 Options 宽高，结合显示区域，计算采样率，降低加载图片的使用内存
        inSampleSize = adjustSampleSize(options = this, width, height)
    }
    return BitmapFactory.decodeResource(res, resid, options.apply {
        inJustDecodeBounds = false // 真正加载图片
    })
}

//--------不改变大小----质量压缩-----------
/**
 * 图像质量压缩
 * @param format 压缩图像的格式
 * @param quality 提示压缩机，0-100。 根据 Bitmap.CompressFormat 不同，该值的解释也不同。
 * @param stream –写入压缩数据的输出流。
 * @return 如果成功压缩到指定的流，则为 true
 */
fun compressBitmap(bitmap: Bitmap, quality: Int): Bitmap {
    val baos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos)
    val byte = baos.toByteArray()
    val ins = ByteArrayInputStream(byte)
    val bm = BitmapFactory.decodeStream(ins)
    ins.close()
    baos.close()
    return bm
}

/**
 * 通过矩阵缩放
 */
fun matrixBitmap(bitmap: Bitmap, scale: Float): Bitmap {
    val matrix = Matrix()
    matrix.setScale(scale, scale)
    return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
}