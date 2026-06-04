package org.example

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SingleLinkedListTest {

    private lateinit var list: SingleLinkedList

    @BeforeEach
    fun setUp() {
        list = SingleLinkedList()
    }

    @Test
    fun `add elements`() {
        list.add(1)
        list.add(2)
        list.add(3)

        assertEquals(3, list.size)
        for (i in list.withIndex()) {
            assertEquals(i.index + 1, i.value)
        }
    }

    @Test
    fun `addFirst works correctly`() {
        list.add(2)
        list.addFirst(1)

        assertEquals(1, list[0])
        assertEquals(2, list[1])
    }

    @Test
    fun `remove element`() {
        list.add(1)
        list.add(2)
        list.add(3)

        assertTrue(list.remove(2))
        assertEquals(2, list.size)
        assertEquals(1, list[0])
        assertEquals(3, list[1])
    }

    @Test
    fun `remove non-existing element`() {
        list.add(1)
        list.add(2)

        val removed = list.remove(3)
        assertFalse(removed)
        assertEquals(2, list.size)
    }

    @Test
    fun `contains works`() {
        list.add(1)
        list.add(2)

        assertTrue(list.contains(1))
        assertFalse(list.contains(3))
    }

    @Test
    fun `get by index`() {
        list.add(10)
        list.add(20)
        list.add(30)

        assertEquals(10, list[0])
        assertEquals(20, list[1])
        assertEquals(30, list[2])
    }

    @Test
    fun `get throws exception on invalid index`() {
        list.add(10)
        list[0]

        assertThrows(IndexOutOfBoundsException::class.java) {
            list[5]
        }
    }

    @Test
    fun `indexOf works`() {
        list.add(10)
        list.add(20)
        list.add(30)

        assertEquals(1, list.indexOf(20))
    }

    @Test
    fun `set works`() {
        list.add(10)
        list.add(20)
        list.add(30)

        list[1] = 5
        assertEquals(5, list[1])
    }

    // тест №1
    @Test
    fun `iterator returns elements in correct order`() {
        list.add(100)
        list.add(200)
        list.add(300)
        
        val iterator = list.iterator()
        assertTrue(iterator.hasNext())
        assertEquals(100, iterator.next())
        assertEquals(200, iterator.next())
        assertEquals(300, iterator.next())
        assertFalse(iterator.hasNext())
    }

    // тест №2
    @Test
    fun `clear removes all elements`() {
        list.add(10)
        list.add(20)

        assertEquals(2, list.size)

        list.remove(10)
        list.remove(20)

        assertEquals(0, list.size)
        assertFalse(list.iterator().hasNext())
    }

    // тест №3
    @Test
    fun `remove the only one element`() {
        list.add(100)
        val removed = list.remove(100)

        assertTrue(removed)
        assertEquals(0, list.size)
        assertThrows(IndexOutOfBoundsException::class.java) {
            list[0]
        }
    }
}
