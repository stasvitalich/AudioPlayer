package com.example.audioplayer

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
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

        val radioList = ArrayList<RadioModel>()
        radioList.add(RadioModel(R.drawable.animeradio, "Anime"))
        radioList.add(RadioModel(R.drawable.retrofm, "Anime"))
        radioList.add(RadioModel(R.drawable.heartfm, "Anime"))
        radioList.add(RadioModel(R.drawable.dorozhnoe, "Anime"))
        radioList.add(RadioModel(R.drawable.country, "Anime"))
        radioList.add(RadioModel(R.drawable.europaplus, "Anime"))
        radioList.add(RadioModel(R.drawable.radiosvoboda, "Anime"))
        radioList.add(RadioModel(R.drawable.nasheradio, "Anime"))
        radioList.add(RadioModel(R.drawable.kpopradio, "Anime"))
        radioList.add(RadioModel(R.drawable.echo, "Anime"))
        radioList.add(RadioModel(R.drawable.radiodacha, "Anime"))
        radioList.add(RadioModel(R.drawable.radioshanson, "Anime"))
        radioList.add(RadioModel(R.drawable.classicrockradio, "Anime"))
        radioList.add(RadioModel(R.drawable.radiomayak, "Anime"))

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
                service?.pauseMusic()
                buttonPlayCarousel.isEnabled = true
                isServiceRunning = false
                buttonStopCarousel.isEnabled = false
            }
        }

    }


}