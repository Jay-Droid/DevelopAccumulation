package com.jay.develop.android.camera

import android.graphics.Bitmap
import android.graphics.Matrix
import android.text.TextUtils
import com.google.zxing.*
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import java.util.Hashtable


/**
 * 二维码扫描工具类
 */
object QRCodeUtils {

    /**
     * 生成二维码图片
     *
     * @param text 编码的文本信息
     * @param w 二维码的宽度
     * @param h 二维码的高度
     * @param logo 在二维码中间嵌入的图像
     * @return 二维码图像
     */
    fun createImage(text: String, w: Int, h: Int, logo: Bitmap?): Bitmap? {
        if (TextUtils.isEmpty(text)) {
            return null
        }
        try {
            val scaleLogo = getScaleLogo(logo, w, h)

            var offsetX = w / 2
            var offsetY = h / 2

            var scaleWidth = 0
            var scaleHeight = 0
            if (scaleLogo != null) {
                scaleWidth = scaleLogo.width
                scaleHeight = scaleLogo.height
                offsetX = (w - scaleWidth) / 2
                offsetY = (h - scaleHeight) / 2
            }
            val hints = Hashtable<EncodeHintType, Any>()
            hints[EncodeHintType.CHARACTER_SET] = "utf-8"
            //容错级别
            hints[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.H
            //设置空白边距的宽度
            hints[EncodeHintType.MARGIN] = 0
            val bitMatrix = QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, w, h, hints)
            val pixels = IntArray(w * h)
            for (y in 0 until h) {
                for (x in 0 until w) {
                    if (x >= offsetX && x < offsetX + scaleWidth && y >= offsetY && y < offsetY + scaleHeight) {
                        var pixel = scaleLogo!!.getPixel(x - offsetX, y - offsetY)
                        if (pixel == 0) {
                            if (bitMatrix.get(x, y)) {
                                pixel = -0x1000000
                            } else {
                                pixel = -0x1
                            }
                        }
                        pixels[y * w + x] = pixel
                    } else {
                        if (bitMatrix.get(x, y)) {
                            pixels[y * w + x] = -0x1000000
                        } else {
                            pixels[y * w + x] = -0x1
                        }
                    }
                }
            }
            val bitmap = Bitmap.createBitmap(
                w, h,
                Bitmap.Config.RGB_565
            )
            bitmap.setPixels(pixels, 0, w, 0, 0, w, h)
            return bitmap
        } catch (e: WriterException) {
            e.printStackTrace()
        }

        return null
    }

    private fun getScaleLogo(logo: Bitmap?, w: Int, h: Int): Bitmap? {
        if (logo == null) {
            return null
        }
        val matrix = Matrix()
        val scaleFactor = Math.min(w * 1.0f / 5f / logo.width.toFloat(), h * 1.0f / 5f / logo.height.toFloat())
        matrix.postScale(scaleFactor, scaleFactor)
        return Bitmap.createBitmap(logo, 0, 0, logo.width, logo.height, matrix, true)
    }

}


