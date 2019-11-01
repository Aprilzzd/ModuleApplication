package com.example.mytest

import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

fun main(){
//    println(binarySearch(9))
//    println(erweiArray(15))
    val data=BigDecimal("123.1")
    println(data.stripTrailingZeros())
    println(data.setScale(2, RoundingMode.HALF_UP))
}

/**
 * 二分查找法
 * O(logn)
 */
fun binarySearch(x:Int):Int{
    val array= intArrayOf(1,2,3,4,5,6,7,8,9)
    var low=0
    var high=array.size-1
    while (low<=high){
        val middle=(low+high)/2
        println(middle)
        when {
            array[middle]==x -> return middle
            array[middle]>x -> high=middle-1
            else -> low=middle+1
        }
    }
    return -1
}

/**
 * 查找重复
 */
fun lookupRepeat(){
    val array= intArrayOf(8,3,1,5,2,1,1,2)
    val result= mutableListOf<Int>()
    for (i in array.indices){
        for (j in i+1 until array.size){
            if (array[i]==array[j]){
                if (result.indexOf(array[i])==-1){
                    println(array[i])
                    result.add(array[i])
                }
            }
        }
    }
}

/**
 * 二维数组查找
 */
fun erweiArray(x:Int):Boolean{
    val array= intArrayOf(9,8,7,6)
    val array1= intArrayOf(0,5,4,3,13)
    val array2= intArrayOf(11,10,1,2,12,14)
    val arrayTwo= arrayOf(array,array1,array2)
    for (i in arrayTwo.indices){
        for (j in arrayTwo[i].indices){
            if (x==arrayTwo[i][j]) return true
        }
    }
    return false
}

fun printLinkedList(){
    val list=LinkedList<String>()
    list.add("1")
    list.add("2")
    list.add("3")
    list.add("4")
    val iterator=list.listIterator()
    list.last
    while (iterator.hasNext()){
        println(iterator.next())
    }
}

fun printEach(){

}