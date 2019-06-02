package com.kotlin.集合与数组与区间


fun main() {
    println("Hello Kotlin")
    //.. rangeTo 运算符  public operator fun rangeTo(other: Int): IntRange
    val range: IntRange = 0..1024 //[0,1024]
    //until  public infix fun Int.until(to: Int): IntRange
    val range2: IntRange = 0 until 1024 // [0,1024)
    println(range)//0..1024
    println(range2)//0..1023
    val emptyRange: IntRange = 0..-1
    println(emptyRange.isEmpty())
    println(range.contains(50))
    println(50 in range)
    for (i in range2) {
        print(" ,$i")
    }


}


