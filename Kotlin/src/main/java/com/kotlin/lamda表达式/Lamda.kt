package com.kotlin.lamda表达式

fun main(args: Array<String>) {
    println("Hello Kotlin")
    println(sum(1L, 2L))
    println(sum.invoke(1L, 2L))//运算符重载
    println(sum(3, 4))
    /**
     * Array的扩展方法
     * action的类型是一个lamda表达式类型
     * public inline fun <T> Array<out T>.forEach(action: (T) -> Unit): Unit {
    for (element in this) action(element)
    }
     */

    // lambda只有一个参数可以默认为it（iterate）
    args.forEach({ it ->
        println(it)
    })
    //只要函数的最后一个参数是一个lambda表达式就可以将参数移到货号外
    args.forEach() {
        println(it)
    }
    //如果括号无用可以去掉
    args.forEach {
        println(it)
    }

    //入参返回值与形参返回值一致的函数可以用函数引用的方式作为实参传入
    //::来引用函数名
    args.forEach(::println)

    //添加循环标签
    args.forEach ForEach@{
        if (it == "a") return ForEach@
        println(it)
    }


    val a2 = { a: Int, b: Int ->
        a + b
    }
    println(sum3(4, a2))
}

//lambda表达式
val sum = { a: Long, b: Long ->
    println("a+b=${a + b}")
    a + b
}

fun sum3(a: Int, a2: (b: Int, c: Int) -> Int): Int {
    println(a2(2, 3))
    return a + a2(2, 3)
}


//匿名函数
val a = fun(x: Int): Long {
    return x.toLong()
}

fun sum(a: Int, b: Int): Int {
    return a + b
}


