package com.example.newsapp.retrofitapi

import com.example.newsapp.Model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("everything")
    fun searchNews(
        @Query("q") query: String,
        @Query("apikey") apikey: String = "cda439f4b1a343fca3203a65954dd949"
    ) : Call<NewsResponse>
}