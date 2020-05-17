package com.kotlin


/**
 * 身份证号码最短长度
 */
const val IDCARD_LENGTH = 18


fun main() {

    val num = "371522199204248792"
    val gender = "女"
    println(checkIdNumberEnable(num, gender))
}

/**
 * 前端&移动端&后端 身份证号码统一校验规则 全局
 * 【身份证件号码】校验要求：
 * 目前仅支持使用的接口仅支持二代身份证识别，一代身份证不支持；
 * 二代身份证：身份证号码共18位，由17位本体码和1位校验码组成。
 * 1.长度校验，要求：18位
 * 2.奇偶校验，15到17位是顺序码，表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编订的顺序号，
 * 顺序码的奇数分配给男性，偶数分配给女性，即第17位奇数表示男性，偶数表示女性；校验第17位；
 * 3.第18位是校验码 （校验计算方式较为复杂，本期先不考虑）
 */
private fun checkIdNumberEnable(idNumber: String, gender: String): Boolean {
    //长度校验
    val isLengthAvailable = idNumber.length >= IDCARD_LENGTH
    //奇偶校验
    var isParityAvailable = false
    if (isLengthAvailable) {
        val seventeenthNum = idNumber.substring(IDCARD_LENGTH - 2, IDCARD_LENGTH - 1)
        val genderByCard = getGenderByCard(seventeenthNum.toInt())
        isParityAvailable = gender == genderByCard
        println(seventeenthNum)
        println(genderByCard)
    }
    return isLengthAvailable && isParityAvailable
}

/**
 * 根据第十七位获取性别
 * 第17位奇数表示男性，偶数表示女性；
 */
private fun getGenderByCard(seventeenthNum: Int): String {

    return if (isEvenNUm(seventeenthNum)) "女" else "男"
}

/**
 * 是否是偶数
 * @return true 偶数 false 奇数
 */
private fun isEvenNUm(num: Int): Boolean {
    return num % 2 == 0
}




