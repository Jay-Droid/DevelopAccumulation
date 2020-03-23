package com.kotlin

import java.net.URI
import java.util.*


fun main(args: Array<String>) {
    println("Hello Kotlin")
    val startDate = Calendar.getInstance()
    startDate.set(2020, 1, 1)
    val startTimeInMillis = startDate.time.time
    val threeDay = DateUtils.getDateLong(2)//后天
    val distanceDay = (threeDay - startTimeInMillis) / DateUtils.DAY_1

    for (index in 0 .. distanceDay) {
        val secondDay = DateUtils.getDateLong((index-distanceDay+2).toInt(), DateUtils.yearMonthDayPointFormat)

    }

}


fun checkIpAndPort(s: String): Boolean {
    val re =
        "((http|ftp|https)://)(([a-zA-Z0-9._-]+)|([0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}))(([a-zA-Z]{2,6})|(:[0-9]{1,4})?)"

    return s.matches(re.toRegex())
}

fun checkAddress(s: String): Boolean {
    return s.matches("((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))".toRegex())
}

fun checkPort(s: String): Boolean {
    return s.matches("^[1-9]$|(^[1-9][0-9]$)|(^[1-9][0-9][0-9]$)|(^[1-9][0-9][0-9][0-9]$)|(^[1-6][0-5][0-5][0-3][0-5]$)".toRegex())
}


fun getIP(uri: URI): URI? {
    var effectiveURI: URI? = null

    effectiveURI = try {
        // URI(String scheme, String userInfo, String host, int port, String
        // path, String query,String fragment)
        URI(uri.scheme, uri.userInfo, uri.host, uri.port, null, null, null)
    } catch (var4: Throwable) {
        null
    }

    return effectiveURI
}
