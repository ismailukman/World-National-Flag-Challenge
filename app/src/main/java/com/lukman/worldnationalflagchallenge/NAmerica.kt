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


class NAmerica : AppCompatActivity() {

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
        setContentView(R.layout.activity_n_america)
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
        up = findViewById(R.id.right)
        down = findViewById(R.id.wrong)
        var hscimg = findViewById<TextView>(R.id.hscimg)
        hscore = findViewById(R.id.hscore)
        group = findViewById(R.id.radioGroup1)
        fontawesone = Typeface.createFromAsset(assets, "fasolid900.ttf")
        hscore.text = HScore.toString();

        val rndm = ArrayList<Int>()
        for (i in 0..23) {
            rndm.add(i)
        }
        rndm.shuffle()

        x = rndm[0]
        //Toast.makeText(this, ""+rndm[test], Toast.LENGTH_SHORT).show()
        a = rndm.indexOf(0) // Returns index of first occurrence of 0
        rndm[0] = rndm[a] //rndm.get(10) Returns the object at index 10.
        rndm[a] = x

        x = rndm[23]
        b = rndm.indexOf(23)
        rndm[23] = rndm[b]
        rndm[b] = x
        hscimg.typeface = fontawesone


        val option1 = arrayOf("Jamaica", "Antigua & Barbuda", "Bahamas", "U.S.A") //Antigua & Barbuda
        val option2 = arrayOf("Bahamas", "Belize", "St.Lucia", "Grenada") //Bahamas
        val option3 = arrayOf("Cuba", "Haiti", "Barbados", "Panama") //Barbados
        val option4 = arrayOf("Dominica", "Belize", "El Salvador", "Cuba") //Belize
        val option5 = arrayOf("St.Kitts & Nevis", "Bermuda", "Guatamala", "Honduras") //Bermuda
        val option6 = arrayOf("Cuba", "St.Vincent & Grenadines", "Nicaragua", "Canada") //Canada
        val option7 = arrayOf("Haiti", "Costa Rica", "Panama", "Belize") //Costa Rica
        val option8 = arrayOf("Cuba", "Mexico", "Bahamas", "St.Lucia") //Cuba
        val option9 = arrayOf("U.S.A", "Bermuda", "Dominica", "Bahamas") //Dominica
        val option10 = arrayOf("Haiti", "Greenland", "Barbados", "El Salvador") //El Salvador
        val option11 = arrayOf("Guatemala", "Greenland", "Honduras", "Belize") //Greenland
        val option12 = arrayOf("Mexico", "Nicaragua", "Grenada", "Cuba") //Grenada
        val option13 = arrayOf("Guatemala", "Costa Rica", "Dominica", "Panama") //Guatemala
        val option14 = arrayOf("Grenada", "Belize", "Jamaica", "Haiti") //Haiti
        val option15 = arrayOf("Antigua & Barbuda", "Honduras", "Jamaica", "Mexico") //Honduras
        val option16 = arrayOf("Haiti", "Cuba", "Canada", "Jamaica") //Jamaica
        val option17 = arrayOf("Dominica", "Mexico", "U.S.A", "Panama") //Mexico
        val option18 = arrayOf("Honduras", "Haiti", "Nicaragua", "Cuba") //Nicaragua
        val option19 = arrayOf("Panama", "U.S.A", "Jamaica", "Grenada") //Panama
        val option20 = arrayOf("Canada", "St.Kitts & Nevis", "Cuba", "Belize") //St.Kitts & Nevis
        val option21 = arrayOf("Haiti", "Bermuda", "Mexico", "St.Lucia") //St.Lucia
        val option22 = arrayOf("Dominica", "St.Vincent & Grenadines", "Cuba", "U.S.A") //St.Vincent & Grenadines
        val option23 = arrayOf("Panama", "Canada", "Belize", "U.S.A") //U.S.A



        btn.setOnClickListener {
            show.visibility =  View.GONE
            quiz.visibility =  View.VISIBLE
            flag.setImageResource(R.drawable.antigua_and_barbuda)
            for (i in 0 until group.childCount) {
                (group.getChildAt(i) as RadioButton).text = option1[i]
                Answer = "Antigua & Barbuda"
            }
            test++
        }

        qbtn.setOnClickListener {
//            show.visibility =  View.VISIBLE
//            quiz.visibility =  View.GONE

            when (rndm[test]) {
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

                    flag.setImageResource(R.drawable.bahamas)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option2[i]
                        Answer = "Bahamas"
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
                    flag.setImageResource(R.drawable.barbados)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option3[i]
                        Answer = "Barbados"
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

                    flag.setImageResource(R.drawable.belize)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option4[i]
                        Answer = "Belize"
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
                    flag.setImageResource(R.drawable.bermuda)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option5[i]
                        Answer = "Bermuda"
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

                    flag.setImageResource(R.drawable.canada)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option6[i]
                        Answer = "Canada"
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
                    flag.setImageResource(R.drawable.costa_rica)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option7[i]
                        Answer = "Costa Rica"
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

                    flag.setImageResource(R.drawable.cuba)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option8[i]
                        Answer = "Cuba"
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
                    flag.setImageResource(R.drawable.dominica)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option9[i]
                        Answer = "Dominica"
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

                    flag.setImageResource(R.drawable.el_salvador)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option10[i]
                        Answer = "El Salvador"
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
                    flag.setImageResource(R.drawable.greenland)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option11[i]
                        Answer = "Greenland"
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

                    flag.setImageResource(R.drawable.grenada)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option12[i]
                        Answer = "Grenada"
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
                    flag.setImageResource(R.drawable.guatemala)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option13[i]
                        Answer = "Guatemala"
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

                    flag.setImageResource(R.drawable.haiti)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option14[i]
                        Answer = "Haiti"
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
                    flag.setImageResource(R.drawable.honduras)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option15[i]
                        Answer = "Honduras"
                    }
                    test++

                }
                15 -> {
                    val selected15 = group.checkedRadioButtonId
                    option = findViewById<View>(selected15) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }

                    flag.setImageResource(R.drawable.jamaica)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option16[i]
                        Answer = "Jamaica"
                    }
                    test++

                }
                16 -> {
                    val selected16 = group.checkedRadioButtonId
                    option = findViewById<View>(selected16) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    flag.setImageResource(R.drawable.mexico)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option17[i]
                        Answer = "Mexico"
                    }
                    test++

                }
                17 -> {
                    val selected17 = group.checkedRadioButtonId
                    option = findViewById<View>(selected17) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    flag.setImageResource(R.drawable.nicaragua)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option18[i]
                        Answer = "Nicaragua"
                    }
                    test++

                }
                18 -> {
                    val selected18 = group.checkedRadioButtonId
                    option = findViewById<View>(selected18) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }

                    flag.setImageResource(R.drawable.panama)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option19[i]
                        Answer = "Panama"
                    }
                    test++

                }
                19 -> {
                    val selected19 = group.checkedRadioButtonId
                    option = findViewById<View>(selected19) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    flag.setImageResource(R.drawable.saint_kitts_and_nevis)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option20[i]
                        Answer = "St.Kitts & Nevis"
                    }
                    test++

                }
                20 -> {
                    val selected20 = group.checkedRadioButtonId
                    option = findViewById<View>(selected20) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }

                    flag.setImageResource(R.drawable.saint_lucia)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option21[i]
                        Answer = "St.Lucia"
                    }
                    test++

                }
                21-> {
                    val selected21 = group.checkedRadioButtonId
                    option = findViewById<View>(selected21) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }

                    flag.setImageResource(R.drawable.saint_vincent_and_the_grenadines)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option22[i]
                        Answer = "St.Vincent & Grenadines"
                    }
                    test++

                }
                22 -> {
                    val selected22 = group.checkedRadioButtonId
                    option = findViewById<View>(selected22) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    flag.setImageResource(R.drawable.usa)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option23[i]
                        Answer = "U.S.A"
                    }
                    test++

                }
                23 -> {

                    val selected23 = group.checkedRadioButtonId
                    option = findViewById<View>(selected23) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    if (c < 8) {
                        val builder: AlertDialog.Builder = AlertDialog.Builder(this@NAmerica)
                        builder.setTitle("SCORE")
                        builder.setMessage("Your score is low, Please try again!")
                        builder.setCancelable(false)
                        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
                            val act2 = Intent(this, NAmerica::class.java)
                            startActivity(act2)
                            dialog.cancel()
                            finish()
                        })
                        builder.create().show()
                    } else {
                        Africa.score = Africa.score + c * 3 - w
                        if (HScore < Africa.score) {
                            HScore = Africa.score
                            val builder: AlertDialog.Builder = AlertDialog.Builder(this@NAmerica)
                            builder.setTitle("SCORE")
                            builder.setMessage(" New High Score!!! Your score is:${Africa.score}")
                            builder.setCancelable(false)
                            builder.setPositiveButton("Continue", DialogInterface.OnClickListener { dialog, id ->
                                val act2 = Intent(this, Oceania::class.java)
                                startActivity(act2)
                                dialog.cancel()
                                finish()
                            })
                            builder.create().show()
                        } else {
                            val builder: AlertDialog.Builder = AlertDialog.Builder(this@NAmerica)
                            builder.setTitle("SCORE")
                            builder.setMessage("Your score is:" + Africa.score)
                            builder.setCancelable(false)
                            builder.setPositiveButton("Continue", DialogInterface.OnClickListener { dialog, id ->
                                val act2 = Intent(this, Oceania::class.java)
                                startActivity(act2)
                                dialog.cancel()
                                finish()
                            })
                            builder.create().show()
                        }
                    }
                    flag.setImageResource(R.drawable.namerica)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option5[i]
                    }


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