package com.lukman.worldnationalflagchallenge

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import render.animations.Attention
import render.animations.Render


class Oceania : AppCompatActivity() {

    var fontawesone: Typeface? = null
    lateinit var show: CardView;
    lateinit var quiz: CardView;
    lateinit var group: RadioGroup
    lateinit var option: RadioButton
    lateinit var up: TextView
    lateinit var down: TextView
    lateinit var hscore: TextView

    private var HScore = 0
    var test = 0
    var count:Int = 0
    var c:Int = 0; var w:Int = 0; var a:Int = 0; var b:Int = 0; var x:Int = 0; var score = 0
    var sharedpreferences: SharedPreferences? = null
    var Ans: String? = null; var Answer:kotlin.String? = null
    val PREFS_NAME = "MyPrefsFile"

    var Africa = com.lukman.worldnationalflagchallenge.Africa()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oceania)
        show = findViewById(R.id.show)
        quiz = findViewById( R.id.quiz)

        show.visibility =  View.VISIBLE
        quiz.visibility =  View.GONE

        val settings = getSharedPreferences(PREFS_NAME, 0)
        // Read a string value with key "LastUse"
        // Read a string value with key "LastUse"
        HScore = settings.getInt("Score", 0)
        //String lastUse = settings.getString("LastUse", "");

        var flag = findViewById<ImageView>(R.id.flag)
        var btn = findViewById<Button>(R.id.btn)
        var qbtn = findViewById<Button>(R.id.quizbtn)
         up = findViewById<TextView>(R.id.right)
         down = findViewById<TextView>(R.id.wrong)
        var hscimg = findViewById<TextView>(R.id.hscimg)
         hscore = findViewById<TextView>(R.id.hscore)
        group = findViewById(R.id.radioGroup1)
        fontawesone = Typeface.createFromAsset(assets, "fasolid900.ttf")
        hscore.text = HScore.toString();

        val rndm = ArrayList<Int>()
        for (i in 0..14) {
            rndm.add(i)
        }
        rndm.shuffle()


        //Toast.makeText(this, ""+rndm, Toast.LENGTH_SHORT).show()
        x = rndm[0]
        a = rndm.indexOf(0) // Returns index of first occurrence of 0
        rndm[0] = rndm[a] //rndm.get(10) Returns the object at index 10.
        rndm[a] = x

        //val s = ""+rndm.min() +" - "+ rndm.max()
        //Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
        x = rndm[14]
        b = rndm.indexOf(14)
        rndm[14] = rndm[b]
        rndm[b] = x
        hscimg.typeface = fontawesone
        //Toast.makeText(this, ""+rndm, Toast.LENGTH_SHORT).show()
        val option1 = arrayOf("Fiji", "Australia", "Nauru", "New Zealand") //Australia
        val option2 = arrayOf("Tuvalu", "Tonga", "Kiribati", "Fiji") //Fiji
        val option3 = arrayOf("Solomon Island", "Kiribati", "Samoa", "Marshall Islands") //Kiribati
        val option4 = arrayOf("Marshall Islands", "Palau", "Tuvalu", "Nauru") //Marshall Islands
        val option5 = arrayOf("Australia", "Micronesia", "Vanuatu", "Fiji") //Micronesia
        val option6 = arrayOf("Kiribati", "Palau", "Nauru", "Tuvalu") //Nauru
        val option7 = arrayOf("New Zealand", "Samoa", "Australia", "Tonga") //New Zealand
        val option8 = arrayOf("Vanuatu", "Samoa", "Fiji", "Palau") //Palau
        val option9 = arrayOf("Papua New Guinea", "Micronesia", "Tonga", "Tuvalu") //Papua New Guinea
        val option10 = arrayOf("Palau", "Vanuatu", "Samoa", "Kiribati") //Samoa
        val option11 = arrayOf("Solomon Islands", "Fiji", "Tonga", "Nauru") //Solomon Islands
        val option12 = arrayOf("Nauru", "Samoa", "Tonga", "Vanuatu") //Tonga
        val option13 = arrayOf("Palau", "Tuvalu", "Vanuatu", "Australia") //Tuvalu
        val option14 = arrayOf("Vanuatu", "Micronesia", "Tonga", "Kiribati") //Vanuatu


        btn.setOnClickListener {
            show.visibility =  View.GONE
            quiz.visibility =  View.VISIBLE
            flag.setImageResource(R.drawable.australia);
            for (i in 0 until group.childCount) {
                (group.getChildAt(i) as RadioButton).text = option1[i]
                Answer = "Australia"
            }
            test++
        }

        //Toast.makeText(this, ""+rndm, Toast.LENGTH_SHORT).show()
        qbtn.setOnClickListener {
//            show.visibility =  View.VISIBLE
//            quiz.visibility =  View.GONE

            when (rndm[test]) {

//                0 ->{
//
//                }
                1 -> {
                    val selected1 = group.checkedRadioButtonId
                    option = findViewById<View>(selected1) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }

                    flag.setImageResource(R.drawable.fiji)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option2[i]
                        Answer = "Fiji"
                    }
                    test++

                }
                2 -> {
                    val selected2 = group.checkedRadioButtonId
                    option = findViewById<View>(selected2) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    flag.setImageResource(R.drawable.kiribati)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option3[i]
                        Answer = "Kiribati"
                    }
                    test++

                }
                3 -> {
                    val selected3 = group.checkedRadioButtonId
                    option = findViewById<View>(selected3) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }

                    flag.setImageResource(R.drawable.marshall_islands)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option4[i]
                        Answer = "Marshall Islands"
                    }
                    test++

                }
                4 -> {
                    val selected4 = group.checkedRadioButtonId
                    option = findViewById<View>(selected4) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    flag.setImageResource(R.drawable.micronesia)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option5[i]
                        Answer = "Micronesia"
                    }
                    test++

                }
                5 -> {
                    val selected5 = group.checkedRadioButtonId
                    option = findViewById<View>(selected5) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }

                    flag.setImageResource(R.drawable.nauru)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option6[i]
                        Answer = "Nauru"
                    }
                    test++

                }
                6 -> {
                    val selected6 = group.checkedRadioButtonId
                    option = findViewById<View>(selected6) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    flag.setImageResource(R.drawable.new_zealand)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option7[i]
                        Answer = "New Zealand"
                    }
                    test++

                }
                7 -> {
                    val selected7 = group.checkedRadioButtonId
                    option = findViewById<View>(selected7) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }

                    flag.setImageResource(R.drawable.palau)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option8[i]
                        Answer = "Palau"
                    }
                    test++

                }
                8 -> {
                    val selected8 = group.checkedRadioButtonId
                    option = findViewById<View>(selected8) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    flag.setImageResource(R.drawable.papua_new_guinea)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option9[i]
                        Answer = "Papua New Guinea"
                    }
                    test++

                }
                9 -> {
                    val selected9 = group.checkedRadioButtonId
                    option = findViewById<View>(selected9) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }

                    flag.setImageResource(R.drawable.samoa)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option10[i]
                        Answer = "Samoa"
                    }
                    test++

                }
                10 -> {
                    val selected10 = group.checkedRadioButtonId
                    option = findViewById<View>(selected10) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    flag.setImageResource(R.drawable.solomon_islands)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option11[i]
                        Answer = "Solomon Islands"
                    }
                    test++

                }
                11 -> {
                    val selected11 = group.checkedRadioButtonId
                    option = findViewById<View>(selected11) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }

                    flag.setImageResource(R.drawable.tonga)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option12[i]
                        Answer = "Tonga"
                    }
                    test++

                }
                12 -> {
                    val selected12 = group.checkedRadioButtonId
                    option = findViewById<View>(selected12) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    flag.setImageResource(R.drawable.tuvalu)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option13[i]
                        Answer = "Tuvalu"
                    }
                    test++

                }
                13 -> {
                    val selected13 = group.checkedRadioButtonId
                    option = findViewById<View>(selected13) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }

                    flag.setImageResource(R.drawable.vanuatu)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option14[i]
                        Answer = "Vanuatu"
                    }
                    test++

                }
                14 -> {
                    val selected14 = group.checkedRadioButtonId
                    option = findViewById<View>(selected14) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    if (c < 5) {
                        val builder: AlertDialog.Builder = AlertDialog.Builder(this@Oceania)
                        builder.setTitle("SCORE")
                        builder.setMessage("Your score is low, Please try again!")
                        builder.setCancelable(false)
                        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
                            val act2 = Intent(this, Oceania::class.java)
                            startActivity(act2)
                            dialog.cancel()
                            finish()
                        })
                        builder.create().show()
                    } else {
                        Africa.score = Africa.score + c * 3 - w
                        if (HScore < Africa.score) {
                            HScore = Africa.score
                            val builder: AlertDialog.Builder = AlertDialog.Builder(this@Oceania)
                            builder.setTitle("SCORE")
                            builder.setMessage("New High Score!!! Your score is: ${Africa.score}")
                            builder.setCancelable(false)
                            builder.setPositiveButton("Continue", DialogInterface.OnClickListener { dialog, id ->
                                val act2 = Intent(this, SAmerica::class.java)
                                startActivity(act2)
                                dialog.cancel()
                                finish()
                            })
                            builder.create().show()
                        } else {
                            val builder: AlertDialog.Builder = AlertDialog.Builder(this@Oceania)
                            builder.setTitle("SCORE")
                            builder.setMessage("Your score is:" + Africa.score)
                            builder.setCancelable(false)
                            builder.setPositiveButton("Continue", DialogInterface.OnClickListener { dialog, id ->
                                val act2 = Intent(this, SAmerica::class.java)
                                startActivity(act2)
                                dialog.cancel()
                                finish()
                            })
                            builder.create().show()
                        }
                    }
                    flag.setImageResource(R.drawable.oceania)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option5[i]
                    }
//                    test++
//                    Toast.makeText(this, ""+test, Toast.LENGTH_SHORT).show()

                }
                else -> { // Note the block
                    //print("x is neither 1 nor 2")
                }
            }

        }

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                //Call your function here
                doAnimation()
                handler.postDelayed(this, 2000)//1 sec delay
            }
        }, 0)


    }

    override fun onStop() {
        super.onStop()
        val settings = getSharedPreferences(PREFS_NAME, 0)
        val editor = settings.edit() // Editor object to make preference changes.
        editor.putInt("Score", HScore) // Write "LastUse" key with value
        editor.commit()
    }

    fun doAnimation(){
        // Create Render Class
        var a = Render(this);
        var b = Render(this);
        var c = Render(this);

        // Set Animation
        a.setAnimation(Attention().Bounce(up))
        a.start()

        b.setAnimation(Attention().Bounce(down))
        b.start()

        c.setAnimation(Attention().Shake(hscore))
        c.start()

    }
}