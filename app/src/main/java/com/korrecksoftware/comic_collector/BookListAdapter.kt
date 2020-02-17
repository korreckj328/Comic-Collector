package com.korrecksoftware.comic_collector

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var books = emptyList<Book>()

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_items, parent, false)
        return BookViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val current = books[position]
        holder.bookItemView.text = current.title + " " + current.issueNumber.toString()
    }

    internal fun setBooks(books: List<Book>) {
        this.books = books
        notifyDataSetChanged()
    }

    override fun getItemCount() = books.size


}