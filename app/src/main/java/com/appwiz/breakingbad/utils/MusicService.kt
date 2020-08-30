package com.appwiz.breakingbad.utils

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.appwiz.breakingbad.R


class MusicService : Service() {
    var player: MediaPlayer? = null
    var context: Context? = null
    private var length = 0

    override fun onBind(arg0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer.create(this, R.raw.bb_theme)
        player!!.isLooping = true // Set looping
        player!!.setVolume(100f, 100f)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action.equals("PLAY")) player!!.start()
        else player!!.pause()
        return START_STICKY
    }

    override fun onStart(intent: Intent?, startId: Int) {
        player!!.start()
    }

    fun onUnBind(arg0: Intent?): IBinder? {
        return null
    }

    fun onStop() {
        player!!.stop()
        player!!.release()
        player = null
    }

    fun onPause() {
        player!!.pause()
    }

    fun onHomePressed() {
        player!!.stop()
    }

    fun pauseMusic() {
        if (player!!.isPlaying) {
            player!!.pause()
            length = player!!.currentPosition
        }
    }

    fun resumeMusic() {
        if (player!!.isPlaying == false) {
            player!!.seekTo(length)
            player!!.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (player != null) {
            try {
                player!!.stop()
                player!!.release()
            } finally {
                player = null
            }
        }
    }

}