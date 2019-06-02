package com.kotlin.集合与数组与区间


fun main() {
    println("Hello Kotlin")
    val arrayInt: IntArray = intArrayOf(1, 2, 3)
    arrayInt[0] = 10
    println(arrayInt[0])
    val arrayChar: CharArray = charArrayOf('a', 'b', 'c')
    val arrayString: Array<String> = arrayOf("a", "b", "c")
    val arrayInts: Array<Int> = arrayOf(4, 5, 6)
    for (int in arrayInt) {
        print(" $int")
    }
    println(arrayChar.joinToString(""))
    println(arrayInt.slice(1..2))

}


