package com.example.mytest

import java.util.*

/**
 * 二叉查找树增，查，遍历
 */
class BinarySearchTree {

    var tree:Node?=null

    inner class Node(var value:Int){
        var left:Node?=null
        var right:Node?=null
    }

    fun insert(value: Int):Boolean{
        if (tree==null){
            tree=Node(value)
            return true
        }
        var node=tree
        while (node!=null){
            when {
                value<node.value -> {
                    if (node.left==null){
                        node.left=Node(value)
                        return true
                    }
                    node=node.left
                }
                value>node.value -> {
                    if (node.right==null){
                        node.right=Node(value)
                        return true
                    }
                    node=node.right
                }
                else -> return false
            }
        }
        return false
    }

    fun find(value: Int):Node?{
        var node=tree
        while (node!=null){
            node = when {
                value<node.value -> {
                    node.left
                }
                value>node.value -> {
                    node.right
                }
                else -> return node
            }
        }
        return node
    }

    fun delete(value: Int):Boolean{
        var parent:Node?= null
        var node:Node?=tree

        //查找相应的node以及node的父节点
        //跳出条件没有找到或者找到了
        while (node != null&&node.value!=value){
            parent=node
            node = when {
                value<node.value -> {
                    node.left
                }
                else -> {
                    node.right
                }
            }
        }

        //没有找到
        if (node==null) return false
        if (parent==null){
            tree=null
            return true
        }

        //左右子节点不为空，有问题
        if (node.left!=null&&node.right!=null){
            if (node.value<parent.value){
                //node要删除的节点，parent.left新节点
                parent.left=findMin(node.right)//上链接
                parent.left?.left=node.left//左链接
                if (parent.left?.value!=node.right?.value){
                    parent.left?.right=node.right//右链接
                }
            }else{
                parent.right=findMin(node.right)
                parent.right?.left=node.left
            }
            return true
        }

        //左右子节点都为空
        if (node.left==null&&node.right==null){
            if (node.value<parent.value){
                parent.left=null
            }else{
                parent.right=null
            }
            return true
        }

        //左节点为空
        if (node.left==null){
            if (node.value<parent.value){
                parent.left=node.right
            }else{
                parent.right=node.right
            }
            return true
        }

        //右节点为空
        if (node.right==null){
            if (node.value<parent.value){
                parent.left=node.left
            }else{
                parent.right=node.left
            }
            return true
        }
        return false
    }

    fun findMin(node:Node?):Node?{
        return if (node?.left!=null){
            findMin(node.left)
        }else{
            node
        }
    }

    /**
     * 前序遍历，先左后右
     */
    fun print(node:Node?){
        if (node==null) return
        println(node.value)
        print(node.left)
        print(node.right)
    }

    /**
     * 广度优先遍历
     */
    fun print(){
        val list=LinkedList<Node>()
        if (tree==null) return
        list.push(tree)
        while (!list.isEmpty()){
            val node=list.poll()
            println(node.value)
            if (node.left!=null) list.addLast(node.left)
            if (node.right!=null) list.addLast(node.right)
        }
    }

    /**
     * 树形打印，广度优先遍历
     */
    fun levelIterator(){
        val list=LinkedList<Node>()
        if (tree==null) return
        list.push(tree)
        while (!list.isEmpty()){//每次打印一排
            println("")
            var count=list.size
            while (count>0){//count记录当前队列中的节点数量，循环队列中的节点依次取出
                val node=list.poll()
                print("  "+node.value)
                if (node.left!=null) list.addLast(node.left)
                if (node.right!=null) list.addLast(node.right)
                count--
            }
        }
    }

}

fun main(){
    val b = BinarySearchTree()
    //插入数据
    b.insert(13)
    b.insert(8)
    b.insert(18)
    b.insert(6)
    b.insert(10)
    b.insert(16)
    b.insert(20)

//    b.delete(8)
//    b.delete(20)
//    b.delete(18)

    b.levelIterator()
}