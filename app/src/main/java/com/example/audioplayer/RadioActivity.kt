package com.example.audioplayer

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import com.example.audioplayer.databinding.ActivityRadioBinding
import androidx.recyclerview.widget.LinearLayoutManager

class RadioActivity : AppCompatActivity() {
    lateinit var binding: ActivityRadioBinding
    private val adapter = RadioStationsAdapter()

    private val imageIdList = listOf(
        R.drawable.animeradio,
        R.drawable.retrofm,
        R.drawable.heartfm,
        R.drawable.dorozhnoe,
        R.drawable.country,
        R.drawable.europaplus,
        R.drawable.radiosvoboda,
        R.drawable.nasheradio,
        R.drawable.kpopradio,
        R.drawable.echo,
        R.drawable.radiodacha,
        R.drawable.radioshanson,
        R.drawable.classicrockradio,
        R.drawable.radiomayak
    )


    private var isServiceRunning = false
    private var currentStationIndex = 0

    private var service: MyService? = null

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MyService.LocalBinder
            this@RadioActivity.service = binder.getService()
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
        binding = ActivityRadioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.buttonPrevious.isEnabled = currentStationIndex != 0


        binding.apply {
            buttonPlay.setOnClickListener {
                if (!isServiceRunning) {
                    startService(Intent(this@RadioActivity, MyService::class.java))
                    isServiceRunning = true
                    buttonPlay.isEnabled = false
                    buttonPause.isEnabled = true
                    buttonNext.isEnabled = true
                    if (currentStationIndex > 0) {
                        buttonPrevious.isEnabled = true
                    }
                }
            }

            buttonPause.setOnClickListener {
                service?.pauseMusic()
                buttonPlay.isEnabled = true
                isServiceRunning = false
                buttonPause.isEnabled = false
                if (!isServiceRunning) {
                    buttonNext.isEnabled = false
                    buttonPrevious.isEnabled = false
                }
            }

            buttonNext.setOnClickListener {
                currentStationIndex++
                currentStationIndex %= Constants.RADIOSTATIONS.size
                service?.setCurrentStationIndex(currentStationIndex)
                isServiceRunning = true
                buttonPause.isEnabled = true
                buttonPrevious.isEnabled = true

            }

            buttonPrevious.setOnClickListener {
                if (currentStationIndex == 0) {
                    currentStationIndex = 13
                    service?.setCurrentStationIndex(currentStationIndex)
                } else {
                    currentStationIndex--
                    currentStationIndex %= Constants.RADIOSTATIONS.size
                    service?.setCurrentStationIndex(currentStationIndex)
                    isServiceRunning = true
                    buttonPause.isEnabled = true
                }
            }

        }

        binding.buttonNextActivity.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            finish()
        }

        init()
    }

    private fun init() = with(binding) {
        rcView.layoutManager =
            LinearLayoutManager(this@RadioActivity, LinearLayoutManager.HORIZONTAL, false)

        // add all star items to the adapter
        for (i in 0 until imageIdList.size) {
            val radio = Radio(imageIdList[i])
            adapter.addRadio(radio)
        }
        rcView.adapter = adapter

        adapter.setOnRadioClickListener(object : RadioStationsAdapter.OnRadioClickListener {
            override fun onRadioClick(position: Int) {
                currentStationIndex = position
                service?.setCurrentStationIndex(currentStationIndex)
                isServiceRunning = true
                buttonPause.isEnabled = true
                buttonPrevious.isEnabled = true
                buttonPlay.isEnabled = false
            }
        })

    }
}




