package com.only.kotlinapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.only.kotlinapp.databinding.FragmentHomeBinding

class FragmentHome : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.livestream.setOnClickListener {
            navigate(R.id.action_fragmentHome_to_fragmentLiveStream)
        }
        binding.camera.setOnClickListener {
            navigate(R.id.action_fragmentHome_to_fragmentCamera)
        }
    }
}