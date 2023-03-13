package com.only.kotlinapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.only.kotlinapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: CommentAdapter
    private var count: Int = 0
    private var list = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
//        binding.video.setVideoPath("https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4")
//        binding.video.setOnPreparedListener { binding.video.start() }

        getData()
        val manager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        adapter = CommentAdapter(list)
        binding.recycler.layoutManager = manager
        binding.recycler.adapter = adapter

        val scroll = object : RecyclerView.OnScrollListener() {
            @SuppressLint("NotifyDataSetChanged")
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                recyclerView.post(Runnable {
                    if (!recyclerView.canScrollVertically(1)) {
                        getData()
                        Log.d("TAG", "onScrolled: load data ")
                        adapter.notifyDataSetChanged()
                    }
                })
            }
        }
        binding.recycler.addOnScrollListener(scroll)

//        binding.root.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
//            if (scrollY == v.height) {
//                count++;
//                if (count < 20) {
//                    getData();
//                }
//            }
//        }
    }

    private fun getData() {
        list.add("1")
        list.add("2")
        list.add("3")
        list.add("4")
        list.add("5")
        list.add("6")
        list.add("1")
        list.add("2")
        list.add("3")
        list.add("4")
        list.add("5")
        list.add("6")
    }
}