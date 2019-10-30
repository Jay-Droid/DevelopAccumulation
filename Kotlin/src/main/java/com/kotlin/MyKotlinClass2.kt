package com.kotlin

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser




fun main(args: Array<String>) {
    val oldItem =
        "{\"head_img_url\":\"http://boss-cdn-2018.o3cloud.cn/boss_assistant.png?e\\u003d1572327836\\u0026token\\u003digCQPJ5b9IeJcnR0zLqoL0S5u2wd1yYLuj_DaG6n:SOpfAVmDqztqANzOoYUEuVe1-bE\\u003d\",\"_id\":\"5db7ab26ce6d2ab158db4a50\",\"name\":\"Boss助手\",\"proxy_account_info\":{\"_id\":\"5db7ab26ce6d2a2732cce09c\"},\"type\":20}"
    val newItem =
        "{\"head_img_url\":\"http://boss-cdn-2018.o3cloud.cn/boss_assistant.png?e\\u003d1572327836\\u0026token\\u003digCQPJ5b9IeJcnR0zLqoL0S5u2wd1yYLuj_DaG6n:_u0FuSCMhNwcfvhMIi6Um_CCvUg\\u003d\",\"_id\":\"5db7ab26ce6d2ab158db4a50\",\"name\":\"Boss助手\",\"proxy_account_info\":{\"_id\":\"5db7ab26ce6d2a2732cce09c\"},\"type\":20}"

    val parser = JsonParser()
    val obj = parser.parse(oldItem) as JsonObject
    val parser1 = JsonParser()
    val obj1 = parser1.parse(newItem) as JsonObject

    println(obj == obj1)
}


