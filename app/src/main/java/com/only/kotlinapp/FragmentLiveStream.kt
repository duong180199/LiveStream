package com.only.kotlinapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.only.kotlinapp.databinding.FragmentLivestreamBinding

class FragmentLiveStream: BaseFragment() {
    private lateinit var binding: FragmentLivestreamBinding

    private lateinit var adapter: CommentAdapter
    private var count: Int = 0
    private var list = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLivestreamBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.video.setVideoPath("https://www.pexels.com/video/an-elegant-set-up-of-fruits-and-glasses-of-wine-7681587/")
        binding.video.start()

        getData()
        val layoutManager: LinearLayoutManager = LinearLayoutManager(context)
        adapter = CommentAdapter(list)
        binding.recycler.layoutManager = layoutManager
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
    }

    fun getData() {
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