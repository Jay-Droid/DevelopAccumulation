package com.jay.java.加密

import com.kotlin.encrpt.HmacMD5Util
import java.util.*

fun main(args: Array<String>) {
    val SECRET_KEY_LOCAL = "5c6bb6fcce6d2a1779126215"
    val SECRET_KEY_API = "5c0a1988ce6d2a984c1fe92c"
    val uuid = UUID.randomUUID().toString()
    val time = System.currentTimeMillis()
    val sign: String?
    val accessToken = "fc6d62ee5509716c766623575f4d8e40"
    val isLocal = false
    sign = "$accessToken," + HmacMD5Util.encryptToString(
        "$accessToken:$uuid:$time", if (isLocal) SECRET_KEY_LOCAL else SECRET_KEY_API
    )
    println("sign: $sign")


    println("X-MSG-ID: $uuid,$time")

}


