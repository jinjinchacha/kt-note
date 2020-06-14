package com.jung.ktdemo.ui.delegate

interface Base {
    fun printA()
    fun printB()
    fun printC()
}

class BaseImpl(val s: String) : Base {
    override fun printA() {
        println(s)
    }

    override fun printB() {
        println(s)
    }

    override fun printC() {
        println(s)
    }
}

// 在要實現的接口後面 + by + 委託對象
class Derived(b: Base) : Base by b

class Example {
    var s: String by DelegateString()
}

