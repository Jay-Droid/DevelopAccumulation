package com.kotlin.语法学习.类

class X
class A {
    lateinit var a: X
    lateinit var c: String
    val d: X by lazy {
        X()
    }
    var b: String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }
}

fun main() {
    println("Hello Kotlin")
    val a: A = A()
    println(a.b)
}


