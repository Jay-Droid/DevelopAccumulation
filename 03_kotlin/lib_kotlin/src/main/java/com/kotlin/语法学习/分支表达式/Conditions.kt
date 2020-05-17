package com.kotlin.语法学习.分支表达式


fun main(args: Array<String>) {
    println("Hello Kotlin")
    //完备性
    val userName = if (args.isEmpty()) {
        "a"
    } else {
        "b"
    }
    val x = 4
    //加强版的switch,支持任意类型
    when (x) {
        is Int -> println("is int")
        in 1..100 -> println("in 1-100")
        else -> {
            println("else")
        }
    }

}


