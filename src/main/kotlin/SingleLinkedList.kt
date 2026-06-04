package org.example

import java.util.NoSuchElementException

class SingleLinkedList : CustomList {

    private class Item(var data: Int) {
        var pointer: Item? = null
    }

    private var first: Item? = null
    override var size: Int = 0

    override fun add(element: Int) {
        val newItem = Item(element)
        if (first == null) {
            first = newItem
        } else {
            var current = first
            while (current?.pointer != null) {
                current = current.pointer
            }
            current?.pointer = newItem
        }
        size++
    }

    override fun addFirst(element: Int) {
        val newItem = Item(element)
        newItem.pointer = first
        first = newItem
        size++
    }

    override operator fun get(index: Int): Int {
        if (index !in 0 until size) {
            throw IndexOutOfBoundsException("Index $index is out of range")
        }
        var current = first
        var position = 0
        while (position < index) {
            current = current?.pointer
            position++
        }
        return current?.data ?: throw NoSuchElementException()
    }

    override operator fun set(index: Int, value: Int) {
        if (index !in 0 until size) {
            throw IndexOutOfBoundsException("Index $index is out of range")
        }
        var current = first
        var position = 0
        while (position < index) {
            current = current?.pointer
            position++
        }
        current?.data = value
    }

    override fun indexOf(element: Int): Int {
        var current = first
        var position = 0
        while (current != null) {
            if (current.data == element) {
                return position
            }
            current = current.pointer
            position++
        }
        return -1
    }

    override fun remove(element: Int): Boolean {
        // случай с удалением первого элемента
        if (first?.data == element) {
            first = first?.pointer
            size--
            return true
        }
        
        var previous = first
        while (previous?.pointer != null) {
            if (previous.pointer?.data == element) {
                previous.pointer = previous.pointer?.pointer
                size--
                return true
            }
            previous = previous.pointer
        }
        return false
    }

    override fun iterator(): Iterator<Int> {
        return object : Iterator<Int> {
            private var currentItem: Item? = first
            
            override fun hasNext(): Boolean = currentItem != null
            
            override fun next(): Int {
                val item = currentItem ?: throw NoSuchElementException()
                currentItem = item.pointer
                return item.data
            }
        }
    }

    companion object {
        fun singleLinkedListOf(vararg elements: Int): SingleLinkedList {
            val list = SingleLinkedList()
            for (element in elements) {
                list.add(element)
            }
            return list
        }
    }
}
