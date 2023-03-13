package com.only.kotlinapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.only.kotlinapp.databinding.LayoutItemBinding

class CommentAdapter(private var list: List<String>) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentAdapter.ViewHolder {
        val binding = LayoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CommentAdapter.ViewHolder, position: Int) {
        with(holder) {
            binding.textItem.text = "item $position"
        }
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(val binding: LayoutItemBinding) : RecyclerView.ViewHolder(binding.root)
}