package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.newsapp.Model.Article
import com.example.newsapp.bookmarkutil.BookmarkManger

class BookmarkAdapter(val data : MutableList<Article>, private val onRemoveBookmark: (Article) -> Unit) : Adapter<BookmarkAdapter.viewHolder>(){
    class viewHolder(view : View) : ViewHolder(view){
        val imageView = view.findViewById<ImageView>(R.id.imgBookmark)
        val title = view.findViewById<TextView>(R.id.txtBookmarkTitle)
        val bookmarkButton = view.findViewById<ImageView>(R.id.btnBookmark)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_row_bookmark_recyler_view,parent,false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val curr = data[position]
        holder.title.text = curr.title
        Glide.with(holder.itemView.context)
            .load(curr.urlToImage)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.imageView)

        holder.bookmarkButton.setOnClickListener{
            onRemoveBookmark(curr)
            BookmarkManger(holder.itemView.context).removeBookmark(curr)
        }
    }
}