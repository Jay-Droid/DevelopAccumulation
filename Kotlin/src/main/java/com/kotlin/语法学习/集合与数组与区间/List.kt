package com.kotlin.语法学习.集合与数组与区间

/**

 * Author：Jay On 2019/6/8 16:09

 * Description:

 */
fun main(args: Array<String>) {
    //不可变集合
    println("Hello Kotlin")
    val arr = arrayOf("1", "2", 3, 4, 5)
    val list1 = listOf(1, 2, "3", 4, "5")                // 随意创建
    val list2 = listOf<String>("1", "2", "3", "4", "5")  // 确定元素的值类型
    val list3 = listOf(arr)
    //以下代码是错误的。因为List<E>只能是不可变集合。而add、remove、clear等函数时MutableList中的函数
    //list1.add()
    //list1.remove()
    list1[0]

    // 遍历
    for(value in list1){
        print("$value \t")
    }


    //可变集合
    val mutableList1 = mutableListOf(1,2,"3",4,"5")                // 随意创建
    val mutableList2 = mutableListOf<String>("1","2","3","4","5")  // 确定元素的值类型
    val mutableList3 = mutableListOf(arr)                          // 可传入一个数组
    val mutableList : ArrayList<String>  // 这里的ArrayList<>和Java里面的ArrayList一致

    mutableList1.add("6")  // 添加元素
    mutableList1.add("7")
    mutableList1.remove(1)   // 删除某一元素

    // 遍历
    for(value in mutableList1){
        print("$value \t")
    }

    mutableList1.clear()   // 清空集合
}