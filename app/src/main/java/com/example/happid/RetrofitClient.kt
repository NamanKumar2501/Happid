package com.example.happid

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://crudcrud.com/api/67741b1ce71749a7b1801b91d5c17fb4/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val myApi: MyApi = retrofit.create(MyApi::class.java)

}
