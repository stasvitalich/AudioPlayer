package com.example.audioplayer

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import com.example.audioplayer.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding



    private var isServiceRunning = false
    private var currentStationIndex = 0

    private var service: MyService? = null

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MyService.LocalBinder
            this@MainActivity2.service = binder.getService()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            service = null
        }
    }

    override fun onStart() {
        super.onStart()
        bindService(Intent(this, MyService::class.java), connection, Context.BIND_AUTO_CREATE)
    }


    override fun onStop() {
        super.onStop()
        unbindService(connection)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.textItemPosition.visibility = View.INVISIBLE
        binding.audioLottie.visibility = View.INVISIBLE

        val radioList = ArrayList<RadioModel>()
        radioList.add(RadioModel(R.drawable.animeradio, "Anime"))
        radioList.add(RadioModel(R.drawable.retrofm, "Retro"))
        radioList.add(RadioModel(R.drawable.heartfm, "Heart"))
        radioList.add(RadioModel(R.drawable.dorozhnoe, "Dorozhnoe"))
        radioList.add(RadioModel(R.drawable.country, "Country"))
        radioList.add(RadioModel(R.drawable.europaplus, "Europa"))
        radioList.add(RadioModel(R.drawable.radiosvoboda, "Svoboda"))
        radioList.add(RadioModel(R.drawable.nasheradio, "Nashe"))
        radioList.add(RadioModel(R.drawable.kpopradio, "Kpop"))
        radioList.add(RadioModel(R.drawable.echo, "Echo"))
        radioList.add(RadioModel(R.drawable.radiodacha, "Dacha"))
        radioList.add(RadioModel(R.drawable.radioshanson, "Shanson"))
        radioList.add(RadioModel(R.drawable.classicrockradio, "Rock"))
        radioList.add(RadioModel(R.drawable.radiomayak, "Mayak"))

        val adapter = RadioAdapter(radioList)


        binding.apply {
            carouselRecyclerview.adapter = adapter
            carouselRecyclerview.set3DItem(true)
            carouselRecyclerview.setAlpha(true)
            carouselRecyclerview.setInfinite(true)
        }


        binding.apply {

            var yourPosition = carouselRecyclerview.getSelectedPosition()
            textItemPosition.text = yourPosition.toString()

            adapter.setOnItemSelectedListener { position ->
                yourPosition = position // сохраняем новую позицию элемента

                if (position in 0..13) {
                    textItemPosition.text = "${Constants.RADIONAMES[position]} is playing now"
                }

                if (yourPosition == position) {
                    currentStationIndex = yourPosition
                    service?.setCurrentStationIndex(currentStationIndex)
                }

                if (isServiceRunning) {
                    buttonPlayCarousel.isEnabled = false
                }
                if (!isServiceRunning) {
                    startService(Intent(this@MainActivity2, MyService::class.java))
                    isServiceRunning = true
                    buttonPlayCarousel.isEnabled = false
                    buttonStopCarousel.isEnabled = true

                }
                binding.textItemPosition.visibility = View.VISIBLE
                binding.audioLottie.visibility = View.VISIBLE
            }


        }



        binding.apply {
            buttonPlayCarousel.setOnClickListener {
                if (!isServiceRunning) {
                    startService(Intent(this@MainActivity2, MyService::class.java))
                    isServiceRunning = true
                    buttonPlayCarousel.isEnabled = false
                    buttonStopCarousel.isEnabled = true

                }
            }

            buttonStopCarousel.setOnClickListener {
                binding.audioLottie.visibility = View.INVISIBLE
                binding.textItemPosition.visibility = View.VISIBLE
                binding.textItemPosition.text = "The radio was stopped"
                service?.pauseMusic()
                buttonPlayCarousel.isEnabled = true
                isServiceRunning = false
                buttonStopCarousel.isEnabled = false
            }
        }

        binding.buttonBackToMenu.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }

    }




}