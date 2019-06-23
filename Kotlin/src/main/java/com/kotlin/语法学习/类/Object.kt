package com.kotlin.语法学习.类

/**

 * Author：Jay On 2019/6/2 23:16

 * Description:

 */

object Singleton {
    var a = 0
    fun add() {
        a++
    }

    fun getAa(): Int {
        return a
    }
}

fun main() {
    val single = Singleton
    Thread {
        run {
            for (i in 1..100) {
                single.add()
                println(single.getAa())

            }
        }
    }.start()

}