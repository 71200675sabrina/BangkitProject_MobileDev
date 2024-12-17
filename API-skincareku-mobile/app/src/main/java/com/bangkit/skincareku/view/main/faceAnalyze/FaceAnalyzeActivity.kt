package com.bangkit.skincareku.view.main.faceAnalyze

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.skincareku.databinding.ActivityFaceAnalyzeBinding

class FaceAnalyzeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFaceAnalyzeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFaceAnalyzeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}