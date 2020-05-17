package com.kotlin.语法学习.循环语句


fun main(args: Array<String>) {
    println("Hello Kotlin")

    for (it in args) {
        println(it)
    }

    for ((index, value) in args.withIndex()) {
        println("index=$index---value=$value")
    }

    for (indexAndValue in args.withIndex()) {
        println("index=${indexAndValue.index}---value=${indexAndValue.value}")
    }
}


