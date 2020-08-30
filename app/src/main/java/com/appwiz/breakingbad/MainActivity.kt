package com.appwiz.breakingbad

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.appwiz.breakingbad.ui.*
import com.appwiz.breakingbad.utils.MusicService
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var music: ImageView
    private lateinit var stop: ImageView
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        music = findViewById(R.id.music_play)
        stop = findViewById(R.id.music_stop)

        music.animate().alpha(1F)
        stop.animate().alpha(0.4F)

        music.setOnClickListener { startMusic() }
        stop.setOnClickListener { stopMusic() }

        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        replaceFragment(CharactersFragment())
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_char -> {
                replaceFragment(CharactersFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_epi -> {
                replaceFragment(EpisodesFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_quote -> {
                replaceFragment(QuotesFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_rip -> {
                replaceFragment(DeathsFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    private fun startMusic() {
        music.animate().alpha(0.4F)
        stop.animate().alpha(1F)
        val action = "PLAY"
        val myService = Intent(this@MainActivity, MusicService::class.java)
        myService.action = action
        startService(myService)
    }

     private fun stopMusic() {
        music.animate().alpha(1F)
        stop.animate().alpha(0.4F)
        val action = "PAUSE"
        val myService = Intent(this@MainActivity, MusicService::class.java)
        myService.action = action
        startService(myService)
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        stopMusic()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        stopMusic()
    }
}
