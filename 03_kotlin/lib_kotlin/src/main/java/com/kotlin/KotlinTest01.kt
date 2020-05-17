package com.kotlin

/**
 * Description:
 *
 * @author xuejiewang
 * @date 2019-09-24
 * @version 1.0
 */


fun main(args: Array<String>) {


    /**
     * 第8题
     */
    println("第8题")
    var a = 1
    var b = 1
    var c = 1
    println("a=${a++} b=$b c=$c")
    println("a=$a b=${++b} c=${c--}")
    println("a=$a b=$b c=${++c}")

    /**
     * 第2大题
     */
    println("\n第2大题")
    var x = 12
    var n = 5
    n %= 2
    x %= n
    println("x=$x")
}

