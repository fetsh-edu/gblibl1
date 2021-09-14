package com.example.gb_libs_lesson1

class CountersModel {

    private val counters = mutableListOf(0, 0, 0)

    fun getCounter(index: Int) = counters[index]

    fun next(index: Int): Int {
        counters[index]++
        return getCounter(index)
    }

    fun set(index: Int, value: Int) {
        counters[index] = value
    }
}