package com.kotlin.算法练习.排序算法

val sortArray = listOf<Int>(11, 16, 4, 3, 44, 66, 1, 22)
fun main() {
    print("排序前：")
    printList(sortArray)
    print("排序后：")
    sortByBubble(sortArray)
    printList(sortArray)

}

fun sortByBubble(sortArray: List<Int>) {


}

fun printList(sortArray: List<Int>) {

    for (i in sortArray) {
        print("$i ")
    }
}
