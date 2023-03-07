package com.example.audioplayer


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.audioplayer.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private var myUserName: String? = null
    lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myUserName = intent.getStringExtra(Constants.USER_NAME)
        binding.textName.text = myUserName

        binding.buttonOpenRadio.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            finish()
        }

        binding.buttonExit.setOnClickListener {
            val musicServiceIntent = Intent(this, MyService::class.java)
            stopService(musicServiceIntent)
            finish()
            System.out.close()
        }

        binding.buttonBackMenu.setOnClickListener {
            val intent = Intent(this,SingInActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        binding.buttonBackMenu.setOnClickListener {
            val intent = Intent(this, SingInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}