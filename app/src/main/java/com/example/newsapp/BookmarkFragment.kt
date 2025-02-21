package com.example.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.newsapp.Model.Article
import com.example.newsapp.bookmarkutil.BookmarkManger

class BookmarkFragment : Fragment() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter: BookmarkAdapter
    private lateinit var bookmarkManger: BookmarkManger
    private var data = mutableListOf<Article>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_bookmark, container, false)

        recyclerView = view.findViewById(R.id.bookmarkRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        bookmarkManger = BookmarkManger(requireContext())
        loadarticle()

        return view
    }

    private fun loadarticle() {
        data = bookmarkManger.getBookmarks().toMutableList()
        adapter = BookmarkAdapter(data){ article ->
            bookmarkManger.removeBookmark(article)
            data.remove(article)
            adapter.notifyDataSetChanged()
        }
        recyclerView.adapter = adapter
    }
}