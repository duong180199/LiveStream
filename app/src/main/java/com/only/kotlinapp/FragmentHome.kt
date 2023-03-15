package com.only.kotlinapp

import android.content.Intent
import android.graphics.Camera
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.only.kotlinapp.databinding.FragmentHomeBinding

class FragmentHome : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding

    private val cameraRequest = 1888
    private val camera: Camera? = null

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
        binding.livestream.setOnClickListener { navigate(R.id.action_fragmentHome_to_fragmentLiveStream) }
        binding.camera.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            activity?.startActivityFromFragment(this, cameraIntent, cameraRequest)
        }
    }
}