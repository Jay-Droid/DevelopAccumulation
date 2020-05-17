package com.jay.develop.android.camera

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jay.develop.R

class QRCodeGainActivity : AppCompatActivity() {
    var editText: EditText? = null
    var button: Button? = null
    var button1: Button? = null
    var imageView: ImageView? = null
    var mBitmap: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode_gain)
        initView()
    }

    /**
     * 初始化组件
     */
    private fun initView() {

        editText = findViewById<View>(R.id.edit_content) as EditText
        button = findViewById<View>(R.id.button_content) as Button
        button1 = findViewById<View>(R.id.button1_content) as Button
        imageView = findViewById<View>(R.id.image_content) as ImageView

        /**
         * 生成二维码图片
         */
        button!!.setOnClickListener(View.OnClickListener {
            val textContent = editText!!.text.toString()
            if (TextUtils.isEmpty(textContent)) {
                Toast.makeText(this@QRCodeGainActivity, "您的输入为空!", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            editText!!.setText("")
            mBitmap = QRCodeUtils.createImage(
                textContent,
                400,
                400,
                BitmapFactory.decodeResource(resources, R.drawable.ic_launcher)
            )
            imageView!!.setImageBitmap(mBitmap)
        })

        /**
         * 生成不带logo的二维码图片
         */
        button1!!.setOnClickListener(View.OnClickListener {
            val textContent = editText!!.text.toString()
            if (TextUtils.isEmpty(textContent)) {
                Toast.makeText(this@QRCodeGainActivity, "您的输入为空!", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            editText!!.setText("")
            mBitmap = QRCodeUtils.createImage(textContent, 400, 400, null)
            imageView!!.setImageBitmap(mBitmap)
        })
    }
}
