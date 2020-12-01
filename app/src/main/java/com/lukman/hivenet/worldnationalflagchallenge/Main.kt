package com.lukman.hivenet.worldnationalflagchallenge

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import render.animations.Attention
import render.animations.Render
import java.util.*

private lateinit var world:ImageView
var currentLanguage = "en"
var currentLang = ""
var myLocale: Locale? = null
val Lang_Test = "myLang"
var token = 0
var myl = ""
@Suppress("DEPRECATION")
class Main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        world = findViewById(R.id.world)
        var btn = findViewById<Button>(R.id.button1)
        //setAppLocale("es");

        var sett = getSharedPreferences(Lang_Test, 0)
        token = sett.getInt("lang", 0)

        if(token==0){
            selectLang()
        }else{
            myl = sett.getString("currLang", null).toString()
            //Toast.makeText(this, myl, Toast.LENGTH_LONG).show()
            setLocale(myl)
        }

        currentLanguage = intent.getStringExtra(myl).toString()

        btn.setOnClickListener{
            val main = Intent(this, Home::class.java)
            startActivity(main)
        }

/*        YoYo.with(Techniques.Pulse)
            .duration(1000)
            .repeat(5)
            .playOn(world);*/

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                //Call your function here
                doAnimation()
                handler.postDelayed(this, 2000)//1 sec delay
            }
        }, 0)
    }

    fun setLocale(localeName: String) {
        if (localeName != currentLanguage) {
            myLocale = Locale(localeName)
            val res = resources
            val dm = res.displayMetrics
            val conf = res.configuration
            conf.locale = myLocale
            res.updateConfiguration(conf, dm)
            val refresh = Intent(this, Main::class.java)
            refresh.putExtra(myl, localeName)
            //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            val settings = getSharedPreferences(Lang_Test, 0)
            val editor = settings.edit() // Editor object to make preference changes.
            editor.putInt("lang", token) // Write "LastUse" key with value
            editor.commit()

            startActivity(refresh)
            finish()
        } else {
            //Toast.makeText(this@Main, "Language already selected!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun selectLang() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Select Default Language")
        alertDialog.setIcon(R.drawable.translate)
        val items = arrayOf("English", "اللغة العربية", "Français", "Türkiye")
        val checkedItem = 0
        alertDialog.setSingleChoiceItems(items, checkedItem) { dialog, which ->
            when (which) {
                0 -> {
                    token = 1
                    setLocale("en")
                    myl = "en"
                    setLang()
                }
                1 -> {
                    token = 1
                    setLocale("ar")
                    myl = "ar"
                    setLang()
                }
                2 -> {
                    token = 1
                    setLocale("fr")
                    myl = "fr"
                    setLang()
                }
                3 -> {
                    token = 1
                    setLocale("tr")
                    myl = "tr"
                    setLang()
                }
            }
        }
        val alert = alertDialog.create()
        alert.setCanceledOnTouchOutside(true)
        alert.show()
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

    fun setLang(){
        val settings = getSharedPreferences(Lang_Test, 0)
        val editor = settings.edit() // Editor object to make preference changes.
        editor.putString("currLang", myl) // Write "LastUse" key with value
        editor.commit()
    }

    fun doAnimation(){
        // Create Render Class
        var a = Render(this);

        // Set Animation
        a.setAnimation(Attention().Pulse(world))
        a.start()
    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
        System.exit(0)
    }

}