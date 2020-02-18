package com.korrecksoftware.comic_collector

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class AddBookActivity : AppCompatActivity() {

    private lateinit var bookTitle: EditText
    private lateinit var bookIssue: EditText
    private lateinit var bookPublisher: EditText
    private lateinit var bookPubDate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        bookTitle = findViewById(R.id.book_title)
        bookIssue = findViewById(R.id.book_issue)
        bookPublisher = findViewById(R.id.book_publisher)
        bookPubDate = findViewById(R.id.book_pubDate)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(bookTitle.text) || TextUtils.isEmpty(bookIssue.text) ||
                    TextUtils.isEmpty(bookPublisher.text) || TextUtils.isEmpty(bookPubDate.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val book = Book(0, bookTitle.text.toString(), bookPublisher.text.toString(), bookIssue.text.toString().toInt(), bookPubDate.text.toString())

                replyIntent.putExtra(EXTRA_REPLY, book)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "book_response"
    }
}
