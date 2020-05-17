package com.jay.develop.android.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.content.ClipData
import android.content.ComponentName
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.Parcelable
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.view.View
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.jay.develop.R

import java.io.File
import java.util.ArrayList

class WebTestActivity : AppCompatActivity() {

    private var mWebView: WebView? = null
    //private String TMP_URL = "http://laodongjianguan.chanlytech.com:8088/laodongjianguan/index.php/daohang/daohang/f";
    private val TMP_URL = "http://192.168.10.116:8001/#/Meeting/List?teamId=5cf6414cce6d2a1b70b2f781"
    //private String TMP_URL = "http://192.168.1.130:8080/cims-viewcapture/afrH5/toAuthDesc.do";
    private var mUploadMessage: ValueCallback<Uri>? = null// 表单的数据信息
    private var mUploadCallbackAboveL: ValueCallback<Array<Uri>>? = null
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_test)
        mWebView = findViewById<WebView>(R.id.webView01)
        mWebView!!.loadUrl(TMP_URL)
        mWebView!!.settings.javaScriptEnabled = true
        mWebView!!.settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        val settings = mWebView!!.settings
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.javaScriptEnabled = true
        settings.setSupportZoom(true)

        mWebView!!.webChromeClient = object : WebChromeClient() {

            override fun onShowFileChooser(
                webView: WebView,
                filePathCallback: ValueCallback<Array<Uri>>,
                fileChooserParams: WebChromeClient.FileChooserParams
            ): Boolean {
                mUploadCallbackAboveL = filePathCallback
                take()
                return true
            }


            fun openFileChooser(uploadMsg: ValueCallback<Uri>) {
                mUploadMessage = uploadMsg
                take()
            }

            fun openFileChooser(uploadMsg: ValueCallback<Uri>, acceptType: String) {
                mUploadMessage = uploadMsg
                take()
            }

            fun openFileChooser(uploadMsg: ValueCallback<Uri>, acceptType: String, capture: String) {
                mUploadMessage = uploadMsg
                take()
            }
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == mUploadMessage && null == mUploadCallbackAboveL) {
                return
            }
            val result = if (data == null || resultCode != Activity.RESULT_OK) null else data.data
            if (mUploadCallbackAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, data)
            } else if (mUploadMessage != null) {

                if (result != null) {
                    val path = getPath(
                        applicationContext,
                        result
                    )
                    val uri = Uri.fromFile(File(path))
                    mUploadMessage!!
                        .onReceiveValue(uri)
                } else {
                    mUploadMessage!!.onReceiveValue(imageUri)
                }
                mUploadMessage = null


            }
        }
    }


    @TargetApi(Build.VERSION_CODES.BASE)
    private fun onActivityResultAboveL(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode != FILECHOOSER_RESULTCODE || mUploadCallbackAboveL == null) {
            return
        }

        var results: Array<Uri>? = null

        if (resultCode == Activity.RESULT_OK) {

            if (data == null) {

                results = arrayOf<Uri>(imageUri!!)
            }


        }
        if (results != null) {
            mUploadCallbackAboveL!!.onReceiveValue(results)
            mUploadCallbackAboveL = null
        } else {
            results = arrayOf<Uri>(imageUri!!)
            mUploadCallbackAboveL!!.onReceiveValue(results)
            mUploadCallbackAboveL = null
        }

        return
    }


    private fun take() {
        val imageStorageDir =
            File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyApp")
        // Create the storage directory if it does not exist
        if (!imageStorageDir.exists()) {
            imageStorageDir.mkdirs()
        }
        val file =
            File(imageStorageDir.toString() + File.separator + "IMG_" + System.currentTimeMillis().toString() + ".jpg")
        imageUri = Uri.fromFile(file)

        val cameraIntents = ArrayList<Intent>()
        val captureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val packageManager = packageManager
        val listCam = packageManager.queryIntentActivities(captureIntent, 0)
        for (res in listCam) {
            val packageName = res.activityInfo.packageName
            val i = Intent(captureIntent)
            i.component = ComponentName(res.activityInfo.packageName, res.activityInfo.name)
            i.setPackage(packageName)
            i.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            cameraIntents.add(i)

        }
        val i = Intent(Intent.ACTION_GET_CONTENT)
        i.addCategory(Intent.CATEGORY_OPENABLE)
        i.type = "image/*"
        val chooserIntent = Intent.createChooser(i, "Image Chooser")
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toTypedArray<Parcelable>())
        startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE)
    }

    companion object {
        private val FILECHOOSER_RESULTCODE = 1// 表单的结果回调</span>

        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.KITKAT)
        fun getPath(context: Context, uri: Uri): String? {
            val isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

            // DocumentProvider
            if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
                // ExternalStorageProvider
                if (isExternalStorageDocument(uri)) {
                    val docId = DocumentsContract.getDocumentId(uri)
                    val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    val type = split[0]

                    if ("primary".equals(type, ignoreCase = true)) {
                        return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                    }

                    // TODO handle non-primary volumes
                } else if (isDownloadsDocument(uri)) {

                    val id = DocumentsContract.getDocumentId(uri)
                    val contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"),
                        java.lang.Long.valueOf(id)
                    )

                    return getDataColumn(context, contentUri, null, null)
                } else if (isMediaDocument(uri)) {
                    val docId = DocumentsContract.getDocumentId(uri)
                    val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    val type = split[0]

                    var contentUri: Uri? = null
                    if ("image" == type) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    } else if ("video" == type) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                    } else if ("audio" == type) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                    }

                    val selection = "_id=?"
                    val selectionArgs = arrayOf(split[1])

                    return getDataColumn(context, contentUri, selection, selectionArgs)
                }// MediaProvider
                // DownloadsProvider
            } else if ("content".equals(uri.scheme!!, ignoreCase = true)) {
                return getDataColumn(context, uri, null, null)
            } else if ("file".equals(uri.scheme!!, ignoreCase = true)) {
                return uri.path
            }// File
            // MediaStore (and general)

            return null
        }


        /**
         * Get the value of the data column for this Uri. This is useful for
         * MediaStore Uris, and other file-based ContentProviders.
         *
         * @param context       The context.
         * @param uri           The Uri to query.
         * @param selection     (Optional) Filter used in the query.
         * @param selectionArgs (Optional) Selection arguments used in the query.
         * @return The value of the _data column, which is typically a file path.
         */
        fun getDataColumn(context: Context, uri: Uri?, selection: String?, selectionArgs: Array<String>?): String? {
            var cursor: Cursor? = null
            val column = "_data"
            val projection = arrayOf(column)

            try {
                cursor = context.contentResolver.query(uri!!, projection, selection, selectionArgs, null)
                if (cursor != null && cursor.moveToFirst()) {
                    val column_index = cursor.getColumnIndexOrThrow(column)
                    return cursor.getString(column_index)
                }
            } finally {
                cursor?.close()
                return null
            }
        }

        /**
         * @param uri The Uri to check.
         * @return Whether the Uri authority is ExternalStorageProvider.
         */
        fun isExternalStorageDocument(uri: Uri): Boolean {
            return "com.android.externalstorage.documents" == uri.authority
        }


        /**
         * @param uri The Uri to check.
         * @return Whether the Uri authority is DownloadsProvider.
         */
        fun isDownloadsDocument(uri: Uri): Boolean {
            return "com.android.providers.downloads.documents" == uri.authority
        }


        /**
         * @param uri The Uri to check.
         * @return Whether the Uri authority is MediaProvider.
         */
        fun isMediaDocument(uri: Uri): Boolean {
            return "com.android.providers.media.documents" == uri.authority
        }
    }


}
