package com.korrecksoftware.comic_collector

import org.junit.Test

import org.junit.Assert.*
import org.junit.runners.Parameterized
import java.sql.Date

class BookTest {

    private val title: String = "Action Comics"
    private val publisher: String = "DC Comics"
    private val issueNumber: Int = 1
    private val publicationDate: String = "1938-06-01"

    @Test
    fun testConstructor() {
        val bookOne = Book(0, title, publisher, issueNumber, publicationDate)
        assertTrue(bookOne.title == title)
        assertTrue(bookOne.publisher == publisher)
        assertTrue(bookOne.publicationDate == publicationDate)
    }
}