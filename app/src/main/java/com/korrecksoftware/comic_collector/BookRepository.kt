package com.korrecksoftware.comic_collector

import androidx.lifecycle.LiveData

class BookRepository(private val bookDAO: BookDAO) {
    val allBooks: LiveData<List<Book>> = bookDAO.getTitles()

    suspend fun insert(book: Book) {
        bookDAO.insert(book)
    }
}