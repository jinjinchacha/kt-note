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

class Derived(b: Base) : Base by b
