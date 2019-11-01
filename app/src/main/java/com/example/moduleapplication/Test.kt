package com.example.moduleapplication

import android.content.Context

class Test(ctx: Context) : Base() {

    override var i: Int=1
    get() = field +1

    init {
        println(i)
    }

    companion object{
        fun create(){
            println("companion")
        }
    }

}

open class Base {
    init {
        println("base")
    }

    open val i:Int = 0
}

fun main() {

//    Test().test(2)
//    Singleton.i=1
//    print(Singleton.i)

    val items= listOf(1, 2, 3, 4, 5)
//    items.filter {
//        it%3==0
//    }.forEach {
//        if (it==20) return@forEach
//        println(it)
//    }

    process(0,1) {
            x,y -> x+y
    }

    val isOddNumber = { number: Int ->
        println("number is $number")
        number % 2 == 1
    }

    println(isOddNumber.invoke(100))

    items.fold(0, {
        // 如果一个 lambda 表达式有参数，前面是参数，后跟“->”
            acc: Int, i: Int ->
        print("acc = $acc, i = $i, ")
        val result = acc + i
        println("result = $result")
        // lambda 表达式中的最后一个表达式是返回值：
        result
    })

    // lambda 表达式的参数类型是可选的，如果能够推断出来的话：
    val joinedToString = items.fold("Elements:", { acc, i -> "$acc $i" })

    // 函数引用也可以用于高阶函数调用：
    val product = items.fold(1, Int::times)

    println("joinedToString = $joinedToString")
    println("product = $product")


}

fun Test.test(i:Int){
    this.i=i
    println(this.i)
}

object Singleton{
    var i:Int=0
}

fun process(x: Int, y: Int, operate: (Int, Int) -> Int) {
    println(operate(x, y))
}

fun <T, R> Collection<T>.fold(
    initial: R,
    combine: (acc: R, nextElement: T) -> R
): R {
    var accumulator: R = initial
    for (element: T in this) {
        accumulator = combine(accumulator, element)
    }
    return accumulator
}