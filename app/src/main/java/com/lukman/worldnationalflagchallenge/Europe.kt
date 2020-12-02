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


class Europe : AppCompatActivity() {
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
        setContentView(R.layout.activity_europe)


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
        for (i in 0..42) {
            rndm.add(i)
        }
        rndm.shuffle()

        x = rndm[0]
        //Toast.makeText(this, ""+rndm[test], Toast.LENGTH_SHORT).show()
        a = rndm.indexOf(0) // Returns index of first occurrence of 0
        rndm[0] = rndm[a] //rndm.get(10) Returns the object at index 10.
        rndm[a] = x

        x = rndm[42]
        b = rndm.indexOf(42)
        rndm[42] = rndm[b]
        rndm[b] = x
        hscimg.typeface = fontawesone

        val option1 = arrayOf("Monaco", "Albania", "Denmark", "Austria") //Albania
        val option2 = arrayOf("Austria", "Belarus", "France", "Malta") //Austria
        val option3 = arrayOf("Cyprus", "Moldova", "Belarus", "Latvia") //Belarus
        val option4 = arrayOf("Germany", "Russia", "Norway", "Belgium") //Belgium
        val option5 = arrayOf("Malta", "Bosnia & Herzegovina", "Serbia", "Poland") //Bosnia & Herzegovina
        val option6 = arrayOf("Netherlands", "Moldova", "Bulgaria", "Greece") //Bulgaria
        val option7 = arrayOf("Croatia", "Czech Rep.", "Hungary", "Latvia") //Croatia
        val option8 = arrayOf("Slovenia", "Iceland", "Cyprus", "Estonia") //Cyprus
        val option9 = arrayOf("Russia", "Czech Rep.", "Italy", "Poland") //Czech Rep.
        val option10 = arrayOf("Slovenia", "Liechtenstein", "Finland", "Denmark") //Denmark
        val option11 = arrayOf("Estonia", "Iceland", "Romania", "Hungary") //Estonia
        val option12 = arrayOf("Denmark", "San Mario", "Finland", "Netherlands") //Finland
        val option13 = arrayOf("Sweden", "Norway", "France", "Luxembourg") //France
        val option14 = arrayOf("Malta", "Germany", "Macedonia", "Switzerland") //Germany
        val option15 = arrayOf("Slovakia", "Slovenia", "Serbia", "Greece") //Greece
        val option16 = arrayOf("Portugal", "Ukraine", "Hungary", "Spain") //Hungary
        val option17 = arrayOf("Iceland", "Finland", "Denmark", "Spain") //Iceland
        val option18 = arrayOf("Ukraine", "Ireland", "Sweden", "Spain") //Ireland
        val option19 = arrayOf("Russia", "Luxembourg", "Estonia", "Italy") //Italy
        val option20 = arrayOf("Malta", "Serbia", "Latvia", "Macedonia") //Latvia
        val option21 = arrayOf("Liechtenstein", "Slovenia", "Portugal", "U. Kingdom") //Liechtenstein
        val option22 = arrayOf("Monaco", "Lithuania", "Norway", "Moldova") //Lithuania
        val option23 = arrayOf("Belarus", "Denmark", "Belgium", "Luxembourg") //Luxembourg
        val option24 = arrayOf("Cyprus", "Spain", "Macedonia", "Latvia") //Macedonia
        val option25 = arrayOf("Malta", "Lithuania", "Iceland", "Estonia") //Malta
        val option26 = arrayOf("Switzerland", "Moldova", "Spain", "Hungary") //Moldova
        val option27 = arrayOf("Romania", "Poland", "Liechtenstein", "Monaco") //Monaco
        val option28 = arrayOf("Serbia", "Latvia", "Netherlands", "Ukraine") //Netherlands
        val option29 = arrayOf("Norway", "Macedonia", "San Mario", "Greece") //Norway
        val option30 = arrayOf("Slovakia", "Poland", "Albania", "Croatia") //Poland
        val option31 = arrayOf("Cyprus", "Austria", "Liechtenstein", "Portugal") //Portugal
        val option32 = arrayOf("Ireland", "Greece", "Romania", "Sweden") //Romania
        val option33 = arrayOf("Russia", "Ukraine", "U. Kingdom", "Bulgaria") //Russia
        val option34 = arrayOf("Denmark", "San Marino", "Moldova", "Cyprus") //San Marino
        val option35 = arrayOf("Switzerland", "Monaco", "Ukraine", "Serbia") //Serbia
        val option36 = arrayOf("Germany", "France", "Slovakia", "Croatia") //Slovakia
        val option37 = arrayOf("Malta", "Ireland", "Slovenia", "Latvia") //Slovenia
        val option38 = arrayOf("Portugal", "Spain", "Norway", "Poland") //Spain
        val option39 = arrayOf("Sweden", "Czech Rep.", "Germany", "Lithuania") //Sweden
        val option40 = arrayOf("Malta", "Switzerland", "Greece", "Belarus") //Switzerland
        val option41 = arrayOf("Serbia", "Cyprus", "San Mario", "Ukraine") //Ukraine
        val option42 = arrayOf("United Kingdom", "Denmark", "Finland", "Ireland") //United Kingdom


        btn.setOnClickListener {
            show.visibility =  View.GONE
            quiz.visibility =  View.VISIBLE
            flag.setImageResource(R.drawable.albania);
            for (i in 0 until group.childCount) {
                (group.getChildAt(i) as RadioButton).text = option1[i]
                Answer = "Albania"
            }
            test++;
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

                    flag.setImageResource(R.drawable.austria)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option2[i]
                        Answer = "Austria"
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
                    flag.setImageResource(R.drawable.belarus)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option3[i]
                        Answer = "Belarus"
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

                    flag.setImageResource(R.drawable.belgium)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option4[i]
                        Answer = "Belgium"
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
                    flag.setImageResource(R.drawable.bosnia_and_herzegovina)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option5[i]
                        Answer = "Bosnia & Herzegovina"
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

                    flag.setImageResource(R.drawable.bulgaria)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option6[i]
                        Answer = "Bulgaria"
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
                    flag.setImageResource(R.drawable.croatia)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option7[i]
                        Answer = "Croatia"
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

                    flag.setImageResource(R.drawable.cyprus)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option8[i]
                        Answer = "Cyprus"
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
                    flag.setImageResource(R.drawable.czech_republic)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option9[i]
                        Answer = "Czech Rep."
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

                    flag.setImageResource(R.drawable.denmark)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option10[i]
                        Answer = "Denmark"
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
                    flag.setImageResource(R.drawable.estonia)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option11[i]
                        Answer = "Estonia"
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

                    flag.setImageResource(R.drawable.finland)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option12[i]
                        Answer = "Finland"
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
                    flag.setImageResource(R.drawable.france)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option13[i]
                        Answer = "France"
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

                    flag.setImageResource(R.drawable.germany)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option14[i]
                        Answer = "Germany"
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
                    flag.setImageResource(R.drawable.greece)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option15[i]
                        Answer = "Greece"
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

                    flag.setImageResource(R.drawable.hungary)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option16[i]
                        Answer = "Hungary"
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
                    flag.setImageResource(R.drawable.iceland)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option17[i]
                        Answer = "Iceland"
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
                    flag.setImageResource(R.drawable.ireland)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option18[i]
                        Answer = "Ireland"
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

                    flag.setImageResource(R.drawable.italy)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option19[i]
                        Answer = "Italy"
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
                    flag.setImageResource(R.drawable.latvia)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option20[i]
                        Answer = "Latvia"
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

                    flag.setImageResource(R.drawable.liechtenstein)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option21[i]
                        Answer = "Liechtenstein"
                    }
                    test++

                }
                21 -> {
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

                    flag.setImageResource(R.drawable.lithuania)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option22[i]
                        Answer = "Lithuania"
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
                    flag.setImageResource(R.drawable.luxembourg)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option23[i]
                        Answer = "Luxembourg"
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

                    flag.setImageResource(R.drawable.macedonia)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option24[i]
                        Answer = "Macedonia"
                    }
                    test++

                }
                24 -> {
                    val selected24 = group.checkedRadioButtonId
                    option = findViewById<View>(selected24) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    flag.setImageResource(R.drawable.malta)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option25[i]
                        Answer = "Malta"
                    }
                    test++

                }
                25 -> {
                    val selected25 = group.checkedRadioButtonId
                    option = findViewById<View>(selected25) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }

                    flag.setImageResource(R.drawable.moldova)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option26[i]
                        Answer = "Moldova"
                    }
                    test++

                }
                26 -> {
                    val selected26 = group.checkedRadioButtonId
                    option = findViewById<View>(selected26) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    flag.setImageResource(R.drawable.monaco)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option27[i]
                        Answer = "Monaco"
                    }
                    test++

                }
                27 -> {
                    val selected27 = group.checkedRadioButtonId
                    option = findViewById<View>(selected27) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }

                    flag.setImageResource(R.drawable.netherlands)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option28[i]
                        Answer = "Netherlands"
                    }
                    test++

                }
                28 -> {
                    val selected28 = group.checkedRadioButtonId
                    option = findViewById<View>(selected28) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    flag.setImageResource(R.drawable.norway)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option29[i]
                        Answer = "Norway"
                    }
                    test++

                }
                29 -> {
                    val selected29 = group.checkedRadioButtonId
                    option = findViewById<View>(selected29) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }

                    flag.setImageResource(R.drawable.poland)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option30[i]
                        Answer = "Poland"
                    }
                    test++

                }
                30 -> {
                    val selected30 = group.checkedRadioButtonId
                    option = findViewById<View>(selected30) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    flag.setImageResource(R.drawable.portugal)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option31[i]
                        Answer = "Portugal"
                    }
                    test++

                }
                31 -> {
                    val selected31 = group.checkedRadioButtonId
                    option = findViewById<View>(selected31) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }

                    flag.setImageResource(R.drawable.romania)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option32[i]
                        Answer = "Romania"
                    }
                    test++

                }
                32 -> {
                    val selected32 = group.checkedRadioButtonId
                    option = findViewById<View>(selected32) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    flag.setImageResource(R.drawable.russia)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option33[i]
                        Answer = "Russia"
                    }
                    test++

                }
                33 -> {
                    val selected33 = group.checkedRadioButtonId
                    option = findViewById<View>(selected33) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }

                    flag.setImageResource(R.drawable.san_marino)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option34[i]
                        Answer = "San Marino"
                    }
                    test++

                }
                34 -> {
                    val selected34 = group.checkedRadioButtonId
                    option = findViewById<View>(selected34) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    flag.setImageResource(R.drawable.serbia)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option35[i]
                        Answer = "Serbia"
                    }
                    test++

                }
                35 -> {
                    val selected35 = group.checkedRadioButtonId
                    option = findViewById<View>(selected35) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }

                    flag.setImageResource(R.drawable.slovakia)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option36[i]
                        Answer = "Slovakia"
                    }
                    test++

                }
                36 -> {
                    val selected36 = group.checkedRadioButtonId
                    option = findViewById<View>(selected36) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    flag.setImageResource(R.drawable.slovenia)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option37[i]
                        Answer = "Slovenia"
                    }
                    test++

                }
                37 -> {
                    val selected37 = group.checkedRadioButtonId
                    option = findViewById<View>(selected37) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    flag.setImageResource(R.drawable.spain)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option38[i]
                        Answer = "Spain"
                    }
                    test++

                }
                38 -> {
                    val selected38 = group.checkedRadioButtonId
                    option = findViewById<View>(selected38) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }

                    flag.setImageResource(R.drawable.sweden)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option39[i]
                        Answer = "Sweden"
                    }
                    test++

                }
                39 -> {
                    val selected39 = group.checkedRadioButtonId
                    option = findViewById<View>(selected39) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    flag.setImageResource(R.drawable.switzerland)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option40[i]
                        Answer = "Switzerland"
                    }
                    test++

                }
                40 -> {
                    val selected40 = group.checkedRadioButtonId
                    option = findViewById<View>(selected40) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }

                    flag.setImageResource(R.drawable.ukraine)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option41[i]
                        Answer = "Ukraine"
                    }
                    test++

                }
                41 -> {
                    val selected41 = group.checkedRadioButtonId
                    option = findViewById<View>(selected41) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }

                    flag.setImageResource(R.drawable.united_kingdom)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option42[i]
                        Answer = "United Kingdom"
                    }
                    test++

                }
                42 -> {
                    val selected42 = group.checkedRadioButtonId
                    option = findViewById<View>(selected42) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    if (c < 10) {
                        val builder: AlertDialog.Builder = AlertDialog.Builder(this@Europe)
                        builder.setTitle("SCORE")
                        builder.setMessage("Your score is low, Please try again!")
                        builder.setCancelable(false)
                        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
                            val act2 = Intent(this, Europe::class.java)
                            startActivity(act2)
                            dialog.cancel()
                            finish()
                        })
                        builder.create().show()
                    } else {
                        Africa.score = Africa.score + c * 3 - w
                        if (HScore < Africa.score) {
                            HScore = Africa.score
                            val builder: AlertDialog.Builder = AlertDialog.Builder(this@Europe)
                            builder.setTitle("SCORE")
                            builder.setMessage("New High Score!!!  Your score is:${Africa.score}")
                            builder.setCancelable(false)
                            builder.setPositiveButton("Continue", DialogInterface.OnClickListener { dialog, id ->
                                val act2 = Intent(this, NAmerica::class.java)
                                startActivity(act2)
                                dialog.cancel()
                                finish()
                            })
                            builder.create().show()
                        } else {
                            val builder: AlertDialog.Builder = AlertDialog.Builder(this@Europe)
                            builder.setTitle("SCORE")
                            builder.setMessage("Your score is:" + Africa.score)
                            builder.setCancelable(false)
                            builder.setPositiveButton("Continue", DialogInterface.OnClickListener { dialog, id ->
                                val act2 = Intent(this, NAmerica::class.java)
                                startActivity(act2)
                                dialog.cancel()
                                finish()
                            })
                            builder.create().show()
                        }
                    }
                    flag.setImageResource(R.drawable.europe)
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