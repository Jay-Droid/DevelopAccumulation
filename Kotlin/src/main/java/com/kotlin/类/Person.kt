package com.kotlin.类

fun main() {
    Person.instance
    val person = Person()
}

class Person {

    /*主构造*/
    constructor() {
        println("constructor1")
    }

    /*属性*/
    private var gender: Boolean = true

    /*伴生对象*/
    companion object {
        val instance: Person by lazy {
            Person("yzq", 26)
        }

        /*伴生对象中的初始化代码*/
        init {
            println("companion init 1")
        }

        init {
            println("companion init 2")
        }
    }

    /*次构造方法*/
    constructor(name: String, age: Int) : this() {
        println("constructor2")
    }

    /*初始化代码块*/
    init {
        println("Person init 2,gender:${gender}")
    }

    /*初始化代码块*/
    init {
        println("Person init 1")
    }


}