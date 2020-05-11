package com.kotlin.语法学习.空类型安全


fun main() {
    println("Hello Kotlin")
    空类型安全()
    类型转换()

}

private fun 类型转换() {
    val parent: Parent = Child()
    if (parent is Child) {
        //智能类型转换
        println(parent.getName())
    }

    val string: String? = "Hello"
    val stringNull: String? = null
    if (string is String) {
        println(string.length)
    }

    val parent2 = Parent()
    //安全的类型转换
    val child: Child? = parent2 as? Child
    println(child?.getName())
}

private fun 空类型安全() {
    //? 可空类型
    //?: elese运算符 如果为空执行后面的表达式
    val name1 = getName()
    val name2 = getName() ?: ""
    println(name1?.length)

    //!! 不为空
    val value: String? = "Hello"
    println(value!!.length)
}

fun getName(): String? {
    return null
}

open class Parent {

}

class Child : Parent() {
    fun getName(): String {
        return "child"
    }

}


