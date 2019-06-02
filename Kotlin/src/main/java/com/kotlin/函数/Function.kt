package com.kotlin.函数

/**
 * 注意点
 * 功能单一
 * 见名思意
 * 参数的个数不要太多，降低虚拟机运行效率
 */

fun main(): Unit {
    println("Hello Kotlin")
    print()
    println(a(3))

}

fun print() = println("print")

val a = fun(x: Int): Long {
    return x.toLong()
}


