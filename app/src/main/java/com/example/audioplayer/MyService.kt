package com.example.audioplayer

import android.Manifest
import android.app.*
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat

class MyService : Service() {
    lateinit var player: MediaPlayer

    fun playRadio() {
        if (!player.isPlaying) {
            player.start()
        }
    }

    fun pauseMusic() {
        if (player.isPlaying) {
            player.pause()
        }
    }

    private val NOTIFICATION_ID = 1
    private val CHANNEL_ID = "AudioPlayerChannel"

    private val binder = LocalBinder()

    inner class LocalBinder : Binder() {
        fun getService(): MyService = this@MyService
    }



    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        createNotificationChannel()
        startForeground(NOTIFICATION_ID, createNotification())
        startPlayingAudio()
        return START_NOT_STICKY
    }

    fun setCurrentStationIndex(index: Int) {
        currentStationIndex = index
        player.reset()
        startPlayingAudio()
    }
    private var currentStationIndex = 0
    private fun startPlayingAudio() {
        player = MediaPlayer().apply {
            setDataSource(Constants.RADIOSTATIONS[currentStationIndex])
            prepareAsync()
            setOnPreparedListener { player.start() }
        }
    }

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.stop()
        player.release()
        stopForeground(true)
    }

    /*A method that allows us to completely close the service
    when the application is closed.*/
    override fun onTaskRemoved(rootIntent: Intent) {
        super.onTaskRemoved(rootIntent)
        stopSelf()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    private fun createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Audio Player Channel",
                NotificationManager.IMPORTANCE_LOW
            )
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Audio Player")
            .setContentText("Playing online radio")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
    }
}