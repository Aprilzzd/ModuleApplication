package com.example.mytest

fun main() {
    val array = intArrayOf(4, 8, 9, 1, 10,1,4,5,6, 6, 2, 5)
    sort(array,0,array.size-1)
    println(array.toList())
}

fun sort(array: IntArray,low:Int,high:Int){
    if (low<high){
        var i=low
        var j=high
        val x=array[i]
        while (i<j){
            while (i<j&&array[j]>x)
                j--
            if (i<j)
                array[i++]=array[j]

            while (i<j&&array[i]<x)
                i++
            if (i<j)
                array[j--]=array[i]
        }
        array[i]=x
        sort(array,low,i-1)
        sort(array,i+1,high)
    }
}