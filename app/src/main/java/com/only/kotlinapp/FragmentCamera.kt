package com.only.kotlinapp

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.SurfaceTexture
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.hardware.camera2.CaptureRequest
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.view.*
import com.only.kotlinapp.databinding.FragmentCameraBinding
import java.util.concurrent.Semaphore

class FragmentCamera : BaseFragment() {

    private lateinit var binding: FragmentCameraBinding

    private val cameraRequest = 1888
    private lateinit var cameraManager: CameraManager
    private lateinit var cameraCaptureSession: CameraCaptureSession
    private lateinit var cameraDevice: CameraDevice
    private lateinit var captureRequest: CaptureRequest

    private lateinit var handler: Handler
    private lateinit var handlerThread: HandlerThread

    private var semaphore=  Semaphore(1)

    private var cameraId = "0"

    companion object {
        var CAMERA_FRONT = "1"
        var CAMERA_BACK = "0"
    }

    private var index: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        activity?.startActivityFromFragment(this, cameraIntent, cameraRequest)

        cameraManager = activity?.getSystemService(Context.CAMERA_SERVICE) as CameraManager

        handlerThread = HandlerThread("Wibu never die")
        handlerThread.start()
        handler = Handler(handlerThread.looper)

        binding.texture.surfaceTextureListener = object : TextureView.SurfaceTextureListener {
            override fun onSurfaceTextureAvailable(
                surface: SurfaceTexture,
                width: Int,
                height: Int
            ) {
                openCamera(cameraId)
            }

            override fun onSurfaceTextureSizeChanged(
                surface: SurfaceTexture,
                width: Int,
                height: Int
            ) {
            }

            override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
                return true
            }

            override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {
            }
        }

        binding.btnSwap.setOnClickListener {
            switch()
        }
    }

    @SuppressLint("MissingPermission")
    private fun openCamera(cameraId: String) {
        cameraManager.openCamera(
            cameraId,
            object : CameraDevice.StateCallback() {
                override fun onOpened(camera: CameraDevice) {
                    cameraDevice = camera
                    semaphore.release()
                    val request = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
                    val surface = Surface(binding.texture.surfaceTexture)
                    request.addTarget(surface)

                    cameraDevice.createCaptureSession(
                        listOf(surface),
                        object : CameraCaptureSession.StateCallback() {
                            override fun onConfigured(session: CameraCaptureSession) {
                                cameraCaptureSession = session
                                cameraCaptureSession.setRepeatingRequest(
                                    request.build(),
                                    null,
                                    handler
                                )
                            }

                            override fun onConfigureFailed(session: CameraCaptureSession) {
                            }

                        },
                        handler
                    )
                }

                override fun onDisconnected(camera: CameraDevice) {
                    semaphore.release()
                    cameraDevice.close()
                }

                override fun onError(camera: CameraDevice, error: Int) {
                    semaphore.release()
                    cameraDevice.close()
                    val errorMsg = when (error) {
                        ERROR_CAMERA_DEVICE -> "Fatal (device)"
                        ERROR_CAMERA_DISABLED -> "Device policy"
                        ERROR_CAMERA_IN_USE -> "Camera in use"
                        ERROR_CAMERA_SERVICE -> "Fatal (service)"
                        ERROR_MAX_CAMERAS_IN_USE -> "Maximum cameras in use"
                        else -> "Unknown"
                    }
                    Log.e(
                        FragmentCamera::class.simpleName,
                        "Error when trying to connect camera $errorMsg"
                    )
                }
            },
            handler
        )
    }

    private fun switch() {
        if (cameraId == CAMERA_FRONT) {
            cameraId = CAMERA_BACK
        } else cameraId = CAMERA_FRONT

        cameraCaptureSession.close()
        cameraDevice.close()
        openCamera(cameraId)
    }
}