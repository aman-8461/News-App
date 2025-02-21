package com.example.newsapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.newsapp.Model.Article
import com.example.newsapp.bookmarkutil.BookmarkManger


class HomeAdapter(val data: List<Article>, val context: Context) :
    Adapter<HomeAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val newsTitle = view.findViewById<TextView>(R.id.newsTitle)
        val newsDescription = view.findViewById<TextView>(R.id.newsDesc)
        val newsImage = view.findViewById<ImageView>(R.id.newsImage)
        val bookmark = view.findViewById<ImageView>(R.id.bookmarkButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_row_home_recyler_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curr = data[position]

        holder.newsTitle.text = curr.title
        holder.newsDescription.text = curr.description
        Glide.with(holder.itemView.context)
            .load(curr.urlToImage)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.newsImage)

        holder.newsTitle.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(curr.url))
            holder.itemView.context.startActivity(intent)

        }

        if(BookmarkManger(context).getBookmarks().contains(curr)){
            holder.bookmark.setImageResource(R.drawable.ic_bookmark)
        }
        else{
            holder.bookmark.setImageResource(R.drawable.baseline_bookmark_border_24)

        }

        holder.bookmark.setOnClickListener{
            if(BookmarkManger(context).getBookmarks().contains(curr)){
                BookmarkManger(context).removeBookmark(curr)
                holder.bookmark.setImageResource(R.drawable.baseline_bookmark_border_24)
                Toast.makeText(context,"Bookmark Removed", Toast.LENGTH_SHORT).show()
            }else{
                BookmarkManger(context).saveBookmark(curr)
                holder.bookmark.setImageResource(R.drawable.ic_bookmark)
                Toast.makeText(context,"Bookmark Added", Toast.LENGTH_SHORT).show()
            }
        }

    }
}