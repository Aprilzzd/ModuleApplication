package com.example.mytest

class Test1{
    companion object{
        var num=0
    }

    fun test(){
        num =1
    }
}

fun main(){
    shellSort()
}

fun listOperator(){
    val list = listOf(1, 2, 3, 4)
    val result = list
        .map { i ->
            println("Map $i")
            i * 2
        }
        .filter { i ->
            println("Filter $i")
            i % 3  == 0
        }
    println(result.first())
}

/**
    插入排序，O(n^2)
 */
fun insertTest(){
    var v: Int

    /**
     * list方式，扑克牌排序
     * 集合方式（效率较低），像整理扑克牌一样，遍历集合从第二个元素开始，元素记为i(首次循环)，将
     * 该元素i保存到一个变量v中，将该元素i依次和它之前的元素j比较(二次循环)，如果i<j则移除
     * 元素i并把v（元素i）放到j之前，跳出循环
     */
    var startTime=System.currentTimeMillis()
    val a= mutableListOf(8,3,1,5,2,1)
    for (i in 1 until a.size){
        v=a[i]

        for (j in 0 until i){
            if (a[j]>v){
                a.removeAt(i)
                a.add(j,v)
                break
            }
        }
        println(a)
    }
    println("time:${System.currentTimeMillis()-startTime}")

    /**
     * 数组方式
     * 数组方式（常用），遍历数组从第二个元素开始，记为i(首次循环)，将该元素i保存到一个变量v
     * 中，将该元素i依次和它之前到数组首项的元素j比较(二次循环，倒序)，如果i<j则位置互换，
     * 这样可以把最小的移动到最左
     */
    startTime=System.currentTimeMillis()
    val array= intArrayOf(8,3,1,5,2,1)
    for (i in 1 until array.size){
        v=array[i]

        for (j in i-1 downTo 0){
            if (array[j]>v){
                //如果大于array[i]就和后一个换位置
                array[j+1]=array[j]
                array[j]=v
            }else{
                break
            }

        }
        println(array.toList())
    }
    println("time:${System.currentTimeMillis()-startTime}")
}

/**
 * 希尔排序
 * 插入排序的高效方式，O(nlogn)
 * 插入排序会在数列基本有序的时候效率高，所以希尔排序利用了这一点
 * 也称递减增量排序算法，增量一般取3 * h + 1，用增量将数列分组，对子数列排序
 * 使数列大致有序，当增量为1时整个数列为一组，进行最后的插入排序
 */
fun shellSort(){
    val a = intArrayOf(4, 8, 9, 1, 10, 6, 2, 5)
    var h = 1
    val n = a.size
    while (h < n / 3)
        //常用
        h = 3 * h + 1//4
    while (h >= 1) {
        //增量为h的插入排序
        for (i in h until n) {//下标4到8循环 增量是4
            val v = a[i]    //h=4 i=4 n=8  v是元素4
            var j = i - h   //j=0
            while (j >= 0 && a[j] > v) {//a[0]>a[4]
                a[j + h] = a[j]
                j -= h
            }
            a[j + h] = v
            println(a.toList())
        }
        h /= 3
    }

}

/**
 * 选择排序
 * 遍历数组（首次循环），数组的每个元素依次和该元素之后的每个元素比较（二次循环），如果小于则
 * 交换位置（存在多次交换），这样第一轮找出的是最小的(在最左侧)，第二轮找出的是次小的，以此类推
 */
fun checkSort(){
    val array = intArrayOf(4, 8, 9, 1, 10, 6, 2, 5)
    var v:Int
    for (i in array.indices){
        for (j in i+1 until array.size){
            if (array[i]>array[j]){
                v=array[i]
                array[i]=array[j]
                array[j]=v
            }
        }
    }
    println(array.toList())
}

/**
 * 冒泡排序
 * 遍历数组（首次循环），数组相邻的两个元素依次比较，二次循环的的区间是0到size-i-1
 * （size-i是因为每轮过后最大值会被移动到最右，无需再排，-1是因为二次循环涉及到j+1，否则会出现
 * 数组越界），如果前者大于后者则交换位置，这样第一轮下来最大值会被移动到最右
 */
fun bubbleSort(){
    val array = intArrayOf(4, 8, 9, 1, 10, 6, 2, 5)
    var v:Int
    for (i in array.indices){
        for (j in 0 until array.size-i-1){
            if (array[j]>array[j+1]){
                v=array[j]
                array[j]=array[j+1]
                array[j+1]=v
            }
        }
    }
    println(array.toList())
}

/**
 * 快速排序，平均复杂度O(nlogn),最糟糕时O(n^2),每一趟的时间复杂度是 O(n)
 * ，递归调用的次数相当于递归树的层数O(logn),所以总共的复杂度就是O(nlogn)
 *
 * 从两端找是因为要把大小数都放到两端，如果从一端找的话无法实现（填坑法）
 *
 */
fun quickSort(){
    val array = intArrayOf(4, 8, 9, 1, 10, 6, 2, 5)
    quick_sort(array,0,array.size-1)
//    getIndex(array,0,array.size-1)
    println(array.toList())
}

fun quick_sort(s:IntArray, l:Int, r:Int){//对一个数组进行分治法
    if (l < r) {
        var i = l//i和j分别记录左右两边进度的下标，l和r分别记录数组开始和结束的下标
        var j = r
        val x = s[l]//以第一个数作为基准
        while (i < j) {//当i==j，坑位于中间
            while (i < j && s[j] >= x) // 从右向左找第一个小于x的数
                j--//如果大于x则跳过，下次不用再循环
            if (i < j)
                s[i++] = s[j]//如果小于x则放到左边的坑中，下标+1，此时右边又形成了一个坑

            while (i < j && s[i] < x) // 从左向右找第一个大于等于x的数
                i++
            if (i < j)
                s[j--] = s[i]
        }
        s[i] = x//把基准数放入中间的坑中
        quick_sort(s, l, i - 1) // 递归调用，左边的数组，此时i为基准数的下标
        quick_sort(s, i + 1, r)// 右边的数组
    }
}

fun getIndex(arr:IntArray, low:Int, high:Int) {
    // 基准数据
    if (low<high){
        var high1 = high
        var low1=low
        val tmp = arr[low1]
        while (low1 < high1) {
            // 当队尾的元素大于等于基准数据时,向前挪动high指针
            while (low1 < high1 && arr[high1] >= tmp) {
                high1--
            }
            // 如果队尾元素小于tmp了,需要将其赋值给low
            arr[low1] = arr[high1]
            // 当队首元素小于等于tmp时,向前挪动low指针
            while (low1 < high1 && arr[low1] <= tmp) {
                low1++
            }
            // 当队首元素大于tmp时,需要将其赋值给high
            arr[high1] = arr[low1]

        }
        // 跳出循环时low和high相等,此时的low或high就是tmp的正确索引位置
        // 由原理部分可以很清楚的知道low位置的值并不是tmp,所以需要将tmp赋值给arr[low]
        arr[low1] = tmp
        getIndex(arr,low,low1-1)
        getIndex(arr,low1+1,high)
    }

}