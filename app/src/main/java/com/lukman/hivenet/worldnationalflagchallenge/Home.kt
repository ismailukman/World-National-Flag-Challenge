package com.lukman.hivenet.worldnationalflagchallenge


import android.app.AlertDialog
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import render.animations.*
import androidx.appcompat.app.AppCompatActivity


@Suppress("DEPRECATION")
class Home : AppCompatActivity() {

    private lateinit var audio: ImageView
    private lateinit var help:ImageView
    private lateinit var about:ImageView
    private lateinit var africa: Button
    private lateinit var asia:Button
    private lateinit var europe:Button
    private lateinit var oceania:Button
    private lateinit var namerica:Button
    private lateinit var samerica:Button
    private lateinit var player: MediaPlayer
    var p = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()

        audio =  findViewById<ImageView>(R.id.audio)
        help =  findViewById<ImageView>(R.id.help)
        about = findViewById<ImageView>(R.id.info)
        player = MediaPlayer.create(this, R.raw.afro)
        asia =  findViewById<Button>(R.id.asi)
        samerica =  findViewById<Button>(R.id.sa)
        europe =  findViewById<Button>(R.id.eu)
        namerica =  findViewById<Button>(R.id.na)
        oceania =  findViewById<Button>(R.id.oc)
        africa = findViewById<Button>(R.id.af)

        player.setVolume(0.20F, 0.20F)
        player.isLooping = true
        player.start()

        africa.setOnClickListener {
                val act2 = Intent(this, Africa::class.java)
                startActivity(act2)
            }
        asia.setOnClickListener {
                val act2 = Intent(this, Asia::class.java)
                startActivity(act2)
            }
        europe.setOnClickListener {
                val act2 = Intent(this, Europe::class.java)
                startActivity(act2)
            }
        namerica.setOnClickListener {
                val act2 = Intent(this, NAmerica::class.java)
                startActivity(act2)
            }
        oceania.setOnClickListener {
                val act2 = Intent(this, Oceania::class.java)
                startActivity(act2)
            }
        samerica.setOnClickListener {
                val act2 = Intent(this, SAmerica::class.java)
                startActivity(act2)
            }

        audio.setOnClickListener {
                player!!.pause()
                when (p) {
                    1 -> {
                        p++
                        audio.setImageResource(R.drawable.audionot)
                        player!!.pause()
                    }
                    2 -> {
                        p--
                        audio.setImageResource(R.drawable.audio)
                        player!!.start()
                    }
                }
            }

        audio.setOnLongClickListener {
            selectAudio()
            true
        }

        about.setOnClickListener {
                val act2 = Intent(this, About::class.java)
                startActivity(act2)
        }

        help.setOnClickListener{
            val act2 = Intent(this, Help::class.java)
            startActivity(act2)
        }

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                //Call your function here
                doAnimation()
                handler.postDelayed(this, 10500)//1 sec delay
            }
        }, 0)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.exit -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (player != null) {
            try {
                player!!.stop()
                player!!.release()
            } finally {
                player != null
            }
        }
    }

    fun stopMusic() {
        player!!.stop()
        player!!.release()
        player != null
    }

    private fun selectAudio() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Select Background Audio")
        alertDialog.setIcon(R.drawable.audio)
        val items = arrayOf("Afro", "Game", "War")
        val checkedItem = 0
        alertDialog.setSingleChoiceItems(items, checkedItem) { dialog, which ->
            when (which) {
                0 -> {
                    //Toast.makeText(this, "You selected Afro", Toast.LENGTH_LONG).show()
                    stopSound()
                    player = MediaPlayer.create(this, R.raw.afro)
                    player.isLooping = true
                    player.start()
                }
                1 -> {
                    stopSound()
                    player = MediaPlayer.create(this, R.raw.game)
                    player.isLooping = true
                    player.start()
                    //Toast.makeText(this, "You selected Game", Toast.LENGTH_LONG).show()
                }
                2 -> {
                    stopSound()
                    player = MediaPlayer.create(this, R.raw.war)
                    player.isLooping = true
                    player.start()
                    //Toast.makeText(this, "You selected War", Toast.LENGTH_LONG).show()
                }
            }
        }
        val alert = alertDialog.create()
        alert.setCanceledOnTouchOutside(true)
        alert.show()
    }
    fun pauseSound() {
        if (player != null && player!!.isPlaying) player!!.pause()
    }

    fun stopSound() {
        if (player != null) {
            player!!.stop()
            player!!.release()
            player != null
        }
    }

    fun doAnimation(){
        // Create Render Class
        var a = Render(this);
        var b = Render(this);
        var c = Render(this);
        var d = Render(this);
        var e = Render(this);
        var f = Render(this);

        // Set Animation
        a.setAnimation(Slide().InDown(africa))
        a.start()

        b.setAnimation(Slide().InDown(asia))
        b.start()

        c.setAnimation(Slide().InLeft(europe))
        c.start()

        d.setAnimation(Slide().InRight(namerica))
        d.start()

        e.setAnimation(Slide().InUp(oceania))
        e.start()

        f.setAnimation(Slide().InUp(samerica))
        f.start()
    }

}