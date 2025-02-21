package com.example.newsapp.bookmarkutil

import android.content.Context
import android.widget.Toast
import com.example.newsapp.Model.Article
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BookmarkManger(var context: Context) {
    private val sharedPreferences = context.getSharedPreferences("bookmarks", Context.MODE_PRIVATE)
    private val gson = Gson()
    fun saveBookmark(article: Article) {
        val bookmarks = getBookmarks().toMutableList()
        if (!bookmarks.contains(article)) {
            bookmarks.add(article)
            val json = gson.toJson(bookmarks)
            sharedPreferences.edit().putString("bookmark_articles", json).apply()
        } else {
            Toast.makeText(context, "Already Bookmarked", Toast.LENGTH_SHORT).show()
        }
    }

    fun removeBookmark(article: Article) {
        val bookmarks = getBookmarks().toMutableList()
        if (bookmarks.contains(article)) {
            bookmarks.remove(article)
            val json = gson.toJson(bookmarks)
            sharedPreferences.edit().putString("bookmark_articles", json).apply()
            Toast.makeText(context,"Bookmark Removed", Toast.LENGTH_SHORT).show()        }
    }

    fun getBookmarks(): List<Article> {
        val json = sharedPreferences.getString("bookmark_articles", null) ?: return emptyList()
        val type = object : TypeToken<List<Article>>() {}.type
        return gson.fromJson(json, type)
    }
}
