package com.korrecksoftware.comic_collector

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var bookViewModel: BooksViewModel
    private val addBookActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddBookActivity::class.java)
            startActivityForResult(intent, addBookActivityRequestCode)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = BookListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        bookViewModel = ViewModelProvider(this).get(BooksViewModel::class.java)

        bookViewModel.allBooks.observe(this, Observer { books ->
            // Update the cached copy of the books in the adapter.
            books?.let { adapter.setBooks(it) }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == addBookActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getParcelableExtra<Book>(AddBookActivity.EXTRA_REPLY)?.let {
                val book: Book = data.getParcelableExtra("book_response")!!
                bookViewModel.insert(book)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.not_saved,
                Toast.LENGTH_LONG).show()
        }
    }
}
