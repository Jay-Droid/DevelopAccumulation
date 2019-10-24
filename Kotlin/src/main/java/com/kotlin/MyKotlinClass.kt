package com.kotlin

import java.net.URI


fun main(args: Array<String>) {
    println("Hello Kotlin")
    val ip1 = "htttp://192.168.10.49:80uuu83/"
    println(ip1)
    println(ip1.indexOf("//"))
    val ip2 = ip1.substring(ip1.indexOf("//")).replace("/", "")
    println(ip2)
    println(checkIpAndPort(ip2))
    println(checkAddress("192.68.0.49"))
    println(checkPort("4954"))
    val uri = URI.create(ip1)
    val uri2 = getIP(URI.create(ip1))
    println(uri)
    println(uri2)
    println(uri2?.host)
    println(uri2?.port)
    println(uri2?.scheme)

    println(uri?.host)
    println(uri?.port)
    println(uri?.scheme)
    val ip3 = "http://192.168.10.49:8099983/1"
    println(checkIpAndPort(ip3))




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
