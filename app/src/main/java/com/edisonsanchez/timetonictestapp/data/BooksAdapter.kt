package com.edisonsanchez.timetonictestapp.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edisonsanchez.timetonictestapp.R
import com.squareup.picasso.Picasso


class BooksAdapter(private val allBooks: Books, private val context: Context)
    : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_item_books, parent,
            false)
        return BooksViewHolder(view)
    }

    override fun getItemCount(): Int {
        return allBooks.nbBooks
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val currentBook = allBooks.books[position]
        holder.nameBook.text = currentBook.bookCode
        val pathImage = currentBook.ownerPrefs.oCoverImg
        val urlImage = "https://timetonic.com/live$pathImage"
        Picasso.get().load(urlImage).placeholder(R.mipmap.ic_launcher)
            .into(holder.imageBook)
    }


    class BooksViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val nameBook: TextView = view.findViewById(R.id.book_name)
        val imageBook: ImageView = view.findViewById(R.id.image_book)

    }


}