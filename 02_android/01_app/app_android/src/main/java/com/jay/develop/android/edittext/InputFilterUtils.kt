package com.jay.develop.android.edittext

import android.text.InputFilter

/**
 *  需要过滤的正则和Filters
 *
 */
object InputFilterUtils {

    /**
     * 中文
     */
    const val CHINESE_FILTER = "\\u4E00-\\u9FA5"

    /**
     * 英文
     */
    const val ENGLISH_FILTER = "a-zA-Z"

    /**
     * 中划线  -
     */
    const val CENTER_LINE_FILTER = "-"


    /**
     * 数字
     */
    const val NUMBER_FILTER = "0-9"


    /**
     * 支持 中文
     */
    fun getChineseFilter(): Array<InputFilter?>? {
        val list = mutableListOf<String>(CHINESE_FILTER)
        return InputFilterHelper().getInputFilters(list)
    }

    /**
     * 支持 字母
     */
    fun getEnglishFilter(): Array<InputFilter?>? {
        val list = mutableListOf<String>(ENGLISH_FILTER)
        return InputFilterHelper().getInputFilters(list)
    }

    /**
     * 支持 中文 和 字母
     */
    fun getEnglishOrChineseFilter(): Array<InputFilter?>? {
        val list = mutableListOf<String>(CHINESE_FILTER, ENGLISH_FILTER)
        return InputFilterHelper().getInputFilters(list)
    }

}