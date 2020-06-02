package com.jay.develop.android.edittext

import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.util.Log
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * InputFilter 帮助类 可单独使用 或者在 InputFilterUtils中使用
 * 使用方式：
 *   val list = mutableListOf<String>("正则需要过滤的条件集合")
 *   EditTextView.filters=InputFilterHelper.getInputFilters(list)
 */

class InputFilterHelper {


    /**
     * Pattern对象
     */
    private var mFilterPattern: Pattern? = null

    /**
     * 限制输入框输入的字符数
     */
    private var mMaxLength: LengthFilter? = null

    /**
     * 获取InputFilters
     * @param filtersList 正则过滤条件集合
     * @param maxLength 字符串最大长度
     */
    fun getInputFilters(filtersList: List<String>, maxLength: Int): Array<InputFilter?>? {
        mFilterPattern = genFilterPattern(filtersList)
        mMaxLength = LengthFilter(maxLength)
        return genFilters()
    }

    /**
     *  @param filtersList 正则过滤条件集合
     */
    fun getInputFilters(filtersList: List<String>): Array<InputFilter?>? {
        mFilterPattern = genFilterPattern(filtersList)
        return genFilters()
    }

    /**
     * 生成EditText的filters
     * @return 返回InputFilter数组，供EditText使用个[android.widget.EditText.setFilters]
     */
    private fun genFilters(): Array<InputFilter?>? {
        val inputFilterList = mutableListOf<InputFilter>()
        /**
         * 是否设置输入字符长度限制
         */
        mMaxLength?.let {
            inputFilterList.add(it)
        }
        /**
         * 过滤
         * source 将要插入的字符串，来自键盘输入、粘贴
         * start：source的起始位置
         * end：source的长度
         * dest：EditText中已经存在的字符串
         * dstart：插入点的位置
         * dend：插入点的结束位置 ,一般情况下等于dstart
         */
        inputFilterList.add(InputFilter { source, start, end, dest, dstart, dend ->
            val s: String = innerFilter(source.toString()) ?: ""
            if (s.isEmpty()) {
                s
            } else {
                if (source is Spanned && s.length == source.length) {
                    var sp = SpannableString(s)
                    /**
                     * 输入汉字时，拼音、笔画等属于Spanned，需要返回SpannedString，当输入完成后，才能生成一个汉字
                     * 否则，会将拼音也展示在EditText中
                     * 从source中start到end 中的span复制到dest中，destoff是偏移量
                     */
                    Log.d("Jay", "source:$source")
                    Log.d("Jay", "start:$start")
                    Log.d("Jay", "end:$end")
                    Log.d("Jay", "s:$s")
                    Log.d("Jay", "sp:$sp")
                    TextUtils.copySpansFrom(source, start, end, null, sp, 0)
                    sp
                } else {
                    s
                }
            }

        })
        return inputFilterList.toTypedArray()
    }

    /**
     * 替换
     */
    private fun innerFilter(source: String): String? {
        val matcher = mFilterPattern?.matcher(source)
        return matcher?.replaceAll("")?.trim()
    }

    /**
     * 生成Pattern对象
     * 该Pattern对象支持的正则与filtersList支持的正则相反，
     * 目的是可以通过[Matcher.replaceAll]方法将不在filtersList支持范围的字符都过滤调
     * @param filtersList 所有支持的正则List
     * @return Pattern对象
     */
    private fun genFilterPattern(filtersList: List<String>): Pattern? {
        val regexSb = StringBuilder("[^")
        for (filterHandler in filtersList) {
            regexSb.append(filterHandler)
        }
        regexSb.append("]")
        return Pattern.compile(regexSb.toString())
    }


}