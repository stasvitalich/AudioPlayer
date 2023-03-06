package com.example.audioplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent

import com.example.audioplayer.databinding.ActivityForgotPasswordBinding
import render.animations.Attention
import render.animations.Flip
import render.animations.Render

class ForgotActivity : AppCompatActivity() {



    lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.textAdd.alpha = 0f
        binding.buttonBack.alpha = 0f


        binding.textAdd.postDelayed({
            // Animate imageBiggestCircle6 to show with a fade-in animation
            binding.textAdd.animate()
                .alpha(1f)
                .setDuration(300) // Duration of the fade-in animation
                .start()

            // Start the animation for imageBiggestCircle6
            val render3 = Render(this)
            render3.setAnimation(Attention().Standup(binding.textAdd))
            render3.start()
        }, 300)

        binding.buttonBack.postDelayed({
            // Animate imageBiggestCircle6 to show with a fade-in animation
            binding.buttonBack.animate()
                .alpha(1f)
                .setDuration(300) // Duration of the fade-in animation
                .start()

            // Start the animation for imageBiggestCircle6
            val render3 = Render(this)
            render3.setAnimation(Attention().Swing(binding.buttonBack))
            render3.start()
        }, 1800)

        binding.buttonBack.setOnClickListener {
            finish()
        }

    }
}