package com.example.mytest

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.util.*

interface Maijiu{
    fun maijiu()
}

class Maotai : Maijiu {

    override fun maijiu() {
        println("我卖得是茅台酒。")
    }

}

class Guitai(private var pingpai:Any) : InvocationHandler{

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
        println("销售开始  柜台是： $javaClass")
        method?.invoke(pingpai)
        println("销售结束")
        return null
    }

}

fun main(){
    val maotai= Maotai()
    val handler= Guitai(maotai)
    val maijiu=Proxy.newProxyInstance(ClassLoader.getSystemClassLoader()
        , arrayOf(Maijiu::class.java),handler) as Maijiu
    maijiu.maijiu()

    val map= LinkedHashMap<Int,String>()
    map[1] = "1"
    map[1] = "2"

    val entries = map.entries

    entries.clear()
    println(map)

    val path="/123/456"
    println(path.substring(1, path.indexOf("/", 1)))
}

