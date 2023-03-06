package com.example.audioplayer

import android.content.Intent
import render.animations.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

import com.example.audioplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val SPLASH_TIME_OUT:Long=6000

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val render2 = Render(this)
        render2.setAnimation(Zoom().In(binding.imageBiggestCircle))
        render2.start()

        binding.apply {
            imageSecondCircle.alpha = 0f
            imageLogo.alpha = 0f
            imageWaves.alpha = 0f
            textCoeluso.alpha = 0f
            textMusicApp.alpha = 0f
            mainLayout.alpha = 0f
        }


        // Delay the start of the animation for imageBiggestCircle6
        binding.imageSecondCircle.postDelayed({
            // Animate imageBiggestCircle6 to show with a fade-in animation
            binding.imageSecondCircle.animate()
                .alpha(1f)
                .setDuration(300) // Duration of the fade-in animation
                .start()

            // Start the animation for imageBiggestCircle6
            val render3 = Render(this)
            render3.setAnimation(Zoom().In(binding.imageSecondCircle))
            render3.start()
        }, 300) // Delay in milliseconds



        binding.imageLogo.postDelayed({

            binding.imageLogo.animate()
                .alpha(1f)
                .setDuration(300)
                .start()


            val render4 = Render(this)
            render4.setAnimation(Zoom().In(binding.imageLogo))
            render4.start()
        }, 700)



        binding.textCoeluso.postDelayed({

            binding.textCoeluso.animate()
                .alpha(1f)
                .setDuration(300)
                .start()


            val render5 = Render(this)
            render5.setAnimation(Fade().InUp(binding.textCoeluso))
            render5.start()
        }, 1000)



        binding.imageWaves.postDelayed({

            binding.imageWaves.animate()
                .alpha(1f)
                .setDuration(300)
                .start()


            val render5 = Render(this)
            render5.setAnimation(Fade().InUp(binding.imageWaves))
            render5.start()
        }, 1200)

        binding.textMusicApp.postDelayed({

            binding.textMusicApp.animate()
                .alpha(1f)
                .setDuration(300)
                .start()


            val render5 = Render(this)
            render5.setAnimation(Fade().In(binding.textMusicApp))
            render5.start()
        }, 1800)


        binding.mainLayout.postDelayed({

            binding.mainLayout.animate()
                .alpha(1f)
                .setDuration(50)
                .start()


            val render6 = Render(this)
            render6.setAnimation(Fade().In(binding.mainLayout))
            render6.start()
        }, 2200)

        binding.mainLayout.postDelayed({

            binding.mainLayout.animate()
                .alpha(1f)
                .setDuration(40)
                .start()


            val render6 = Render(this)
            render6.setAnimation(Fade().Out(binding.mainLayout))
            render6.start()
        }, 4700)


        Handler().postDelayed({
            startActivity(Intent(this, SingInActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)

    }
}