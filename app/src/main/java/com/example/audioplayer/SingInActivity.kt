package com.example.audioplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.audioplayer.databinding.ActivitySingInBinding
import render.animations.*

class SingInActivity : AppCompatActivity() {

    lateinit var binding: ActivitySingInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editTextPersonName.alpha = 0f
        binding.textView2.alpha = 0f
        binding.textView3.alpha = 0f
        binding.buttonSingIn.alpha = 0f
        binding.imageLine.alpha = 0f
        binding.imageLine2.alpha = 0f
        binding.buttonForgotPassword.alpha = 0f
        binding.textInputPassword.alpha = 0f

        binding.editTextPersonName.postDelayed({
            // Animate imageBiggestCircle6 to show with a fade-in animation
            binding.editTextPersonName.animate()
                .alpha(1f)
                .setDuration(300) // Duration of the fade-in animation
                .start()

            // Start the animation for imageBiggestCircle6
            val render3 = Render(this)
            render3.setAnimation(Flip().InX(binding.editTextPersonName))
            render3.start()
        }, 300)

        binding.textView2.postDelayed({
            // Animate imageBiggestCircle6 to show with a fade-in animation
            binding.textView2.animate()
                .alpha(1f)
                .setDuration(1800) // Duration of the fade-in animation
                .start()

            // Start the animation for imageBiggestCircle6
            val render3 = Render(this)
            render3.setAnimation(Slide().InLeft(binding.textView2))
            render3.start()
        }, 700)

        binding.textView3.postDelayed({
            // Animate imageBiggestCircle6 to show with a fade-in animation
            binding.textView3.animate()
                .alpha(1f)
                .setDuration(1800) // Duration of the fade-in animation
                .start()

            // Start the animation for imageBiggestCircle6
            val render3 = Render(this)
            render3.setAnimation(Slide().InLeft(binding.textView3))
            render3.start()
        }, 700)


        binding.textInputPassword.postDelayed({
            // Animate imageBiggestCircle6 to show with a fade-in animation
            binding.textInputPassword.animate()
                .alpha(1f)
                .setDuration(300) // Duration of the fade-in animation
                .start()

            // Start the animation for imageBiggestCircle6
            val render3 = Render(this)
            render3.setAnimation(Flip().InX(binding.textInputPassword))
            render3.start()
        }, 500)

        binding.buttonSingIn.postDelayed({
            // Animate imageBiggestCircle6 to show with a fade-in animation
            binding.buttonSingIn.animate()
                .alpha(1f)
                .setDuration(300) // Duration of the fade-in animation
                .start()

            // Start the animation for imageBiggestCircle6
            val render3 = Render(this)
            render3.setAnimation(Flip().InX(binding.buttonSingIn))
            render3.start()
        }, 700)

        binding.buttonForgotPassword.postDelayed({
            // Animate imageBiggestCircle6 to show with a fade-in animation
            binding.buttonForgotPassword.animate()
                .alpha(1f)
                .setDuration(300) // Duration of the fade-in animation
                .start()

            // Start the animation for imageBiggestCircle6
            val render3 = Render(this)
            render3.setAnimation(Fade().InUp(binding.buttonForgotPassword))
            render3.start()
        }, 1400)

        binding.imageLine.postDelayed({
            // Animate imageBiggestCircle6 to show with a fade-in animation
            binding.imageLine.animate()
                .alpha(1f)
                .setDuration(300) // Duration of the fade-in animation
                .start()

            // Start the animation for imageBiggestCircle6
            val render3 = Render(this)
            render3.setAnimation(Fade().InLeft(binding.imageLine))
            render3.start()
        }, 1600)

        binding.imageLine2.postDelayed({
            // Animate imageBiggestCircle6 to show with a fade-in animation
            binding.imageLine2.animate()
                .alpha(1f)
                .setDuration(300) // Duration of the fade-in animation
                .start()

            // Start the animation for imageBiggestCircle6
            val render3 = Render(this)
            render3.setAnimation(Fade().InRight(binding.imageLine2))
            render3.start()
        }, 1600)


        binding.buttonForgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotActivity::class.java)
            startActivity(intent)
        }

        binding.buttonSingIn.setOnClickListener {

            val yourName = binding.editTextPersonName

            if (binding.editTextPersonName.text.isEmpty()){
                binding.editTextPersonName.postDelayed({
                    // Animate imageBiggestCircle6 to show with a fade-in animation
                    binding.editTextPersonName.animate()
                        .alpha(1f)
                        .setDuration(300) // Duration of the fade-in animation
                        .start()

                    // Start the animation for imageBiggestCircle6
                    val render3 = Render(this)
                    render3.setAnimation(Attention().Bounce(binding.editTextPersonName))
                    render3.start()
                }, 1)
            }

            if (binding.textPasswordForInput.text?.isEmpty() == true){
                binding.textInputPassword.postDelayed({
                    // Animate imageBiggestCircle6 to show with a fade-in animation
                    binding.textInputPassword.animate()
                        .alpha(1f)
                        .setDuration(300) // Duration of the fade-in animation
                        .start()

                    // Start the animation for imageBiggestCircle6
                    val render3 = Render(this)
                    render3.setAnimation(Attention().Bounce(binding.textInputPassword))
                    render3.start()
                }, 1)
            }
            else{
                val intent = Intent(this, MenuActivity::class.java)
                intent.putExtra(Constants.USER_NAME, yourName.text.toString())
                startActivity(intent)
                finish()
            }
        }

    }
}


