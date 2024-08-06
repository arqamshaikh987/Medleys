package com.example.medleys

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : ComponentActivity() {

    lateinit var  myRecyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRecyclerView = findViewById(R.id.recyclerView)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData(query = "Akon")

        retrofitData.enqueue(object : Callback<MyData?>{
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                // If the API call is a success then this method is executed
                val dataList = response.body()?.data!!

                myAdapter = MyAdapter(this@MainActivity,dataList)
                myRecyclerView.adapter = myAdapter
                myRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                Log.d("TAG: onResponse", "onResponse: " +response.body())

            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                // If the API call is a failure then this method is executed
                Log.d("TAG: ONFailure", "onFailure: " +t.message)
            }
        })
    }
}

