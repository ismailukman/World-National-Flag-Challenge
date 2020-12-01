package com.lukman.hivenet.worldnationalflagchallenge

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


@Suppress("DEPRECATION")
class Asia : AppCompatActivity() {
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

    var Africa = com.lukman.hivenet.worldnationalflagchallenge.Africa()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asia)

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
        for (i in 0..44) {
            rndm.add(i)
        }
        rndm.shuffle()

        x = rndm[0]
        //Toast.makeText(this, ""+rndm[test], Toast.LENGTH_SHORT).show()
        a = rndm.indexOf(0) // Returns index of first occurrence of 0
        rndm[0] = rndm[a] //rndm.get(10) Returns the object at index 10.
        rndm[a] = x

        x = rndm[44]
        b = rndm.indexOf(44)
        rndm[44] = rndm[b]
        rndm[b] = x
        hscimg.typeface = fontawesone


        val option1 = arrayOf("Afghanistan", "Algeria", "Armenia", "Azerbaijan") //Afghanistan
        val option2 = arrayOf("East Timor", "Armenia", "Bahrain", "India") //Armenia
        val option3 = arrayOf("Brunei", "Iran", "Indonesia", "Azerbaijan") //Azerbaijan
        val option4 = arrayOf("Nepal", "Bhutan", "Bahrain", "Laos") //Bahrain
        val option5 = arrayOf("Bangladesh", "Kazakhstan", "Singapore", "Sri Lanka") //Bangladesh
        val option6 = arrayOf("Thailand", "Maldives", "Bhutan", "Brunei") //Bhutan
        val option7 = arrayOf("Nepal", "Mongolia", "Brunei", "Kuwait") //Brunei
        val option8 = arrayOf("Iran", "Jordan", "China", "Iraq") //China
        val option9 = arrayOf("U.A.E", "Brunei", "East Timor", "Oman") //East Timor
        val option10 = arrayOf("Laos", "India", "Lebanon", "Syria") //India
        val option11 = arrayOf("Kuwait", "Sri Lanka", "Indonesia", "Lebanon") //Indonesia
        val option12 = arrayOf("India", "Singapore", "Pakistan", "Iran") //Iran
        val option13 = arrayOf("Iraq", "Qatar", "Saudi Arabia", "Yemen") //Iraq
        val option14 = arrayOf("Mongolia", "Israel", "Philippines", "Uzbekistan") //Israel
        val option15 = arrayOf("Palestine", "Syria", "Japan", "Korea") //Japan
        val option16 = arrayOf("Tajikistan", "Turkmenistan", "Vietnam", "Jordan") //Jordan
        val option17 = arrayOf("Kazakhstan", "Tajikistan", "Uzbekistan", "Turkey") //Kazakhstan
        val option18 = arrayOf("Qatar", "Nepal", "Kuwait", "Yemen") //Kuwait
        val option19 = arrayOf("Singapore", "Oman", "Lebanon", "Kyrgyzstan") //Kyrgyzstan
        val option20 = arrayOf("Lebanon", "Laos", "Tajikistan", "Syria") //Laos
        val option21 = arrayOf("Armenia", "Mongolia", "Lebanon", "N. Korea") //Lebanon
        val option22 = arrayOf("Malaysia", "Azerbaijan", "Jordan", "Pakistan") //Malaysia
        val option23 = arrayOf("Thailand", "U.A.E", "Maldives", "Sri Lanka") //Maldives
        val option24 = arrayOf("Kyrgyzstan", "Mongolia", "Singapore", "Laos") //Mongolia
        val option25 = arrayOf("Oman", "Jordan", "Japan", "Nepal") //Nepal
        val option26 = arrayOf("N. Korea", "S. Korea", "Turkey", "Turkmenistan") //N. Korea
        val option27 = arrayOf("Yemen", "Thailand", "Oman", "Qatar") //Oman
        val option28 = arrayOf("Uzbekistan", "Pakistan", "Saudi Arabia", "Yemen") //Pakistan
        val option29 = arrayOf("Mongolia", "Thailand", "Kuwait", "Palestine") //Palestine
        val option30 = arrayOf("Palestine", "Maldives", "East Timor", "Philippines") //Philippines
        val option31 = arrayOf("Yemen", "Qatar", "Indonesia", "Bahrain") //Qatar
        val option32 = arrayOf("Saudi Arabia", "Iraq", "Pakistan", "U.A.E") //Saudi Arabia
        val option33 = arrayOf("East Timor", "Maldives", "Singapore", "Thailand") //Singapore
        val option34 = arrayOf("S. Korea", "N. Korea", "China", "Japan") //S. Korea
        val option35 = arrayOf("Vietnam", "Jordan", "Sri Lanka", "Bhutan") //Sri Lanka
        val option36 = arrayOf("Iran", "Yemen", "Qatar", "Syria") //Syria
        val option37 = arrayOf("Laos", "Tajikistan", "Lebanon", "Israel") //Tajikistan
        val option38 = arrayOf("Kuwait", "Armenia", "Thailand", "Afghanistan") //Thailand
        val option39 = arrayOf("Turkey", "N. Korea", "Mongolia", "Oman") //Turkey
        val option40 = arrayOf("Syria", "Yemen", "Turkmenistan", "U.A.E") //Turkmenistan
        val option41 = arrayOf("Philippines", "U.A.E", "Kuwait", "Nepal") //U.A.E
        val option42 = arrayOf("Singapore", "Syria", "Lebanon", "Uzbekistan") //Uzbekistan
        val option43 = arrayOf("Sri Lanka", "Turkey", "Vietnam", "Yemen") //Vietnam
        val option44 = arrayOf("Yemen", "Bahrain", "Malaysia", "Thailand") //Yemen


        btn.setOnClickListener {
            show.visibility =  View.GONE
            quiz.visibility =  View.VISIBLE
            flag.setImageResource(R.drawable.afghanistan);
            for (i in 0 until group.childCount) {
                (group.getChildAt(i) as RadioButton).text = option1[i]
                Answer = "Afghanistan"
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

                    flag.setImageResource(R.drawable.armenia)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option2[i]
                        Answer = "Armenia"
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
                    flag.setImageResource(R.drawable.azerbaijan)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option3[i]
                        Answer = "Azerbaijan"
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

                    flag.setImageResource(R.drawable.bahrain)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option4[i]
                        Answer = "Bahrain"
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
                    flag.setImageResource(R.drawable.bangladesh)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option5[i]
                        Answer = "Bangladesh"
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

                    flag.setImageResource(R.drawable.bhutan)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option6[i]
                        Answer = "Bhutan"
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
                    flag.setImageResource(R.drawable.brunei)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option7[i]
                        Answer = "Brunei"
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

                    flag.setImageResource(R.drawable.china)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option8[i]
                        Answer = "China"
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
                    flag.setImageResource(R.drawable.east_timor)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option9[i]
                        Answer = "East Timor"
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

                    flag.setImageResource(R.drawable.india)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option10[i]
                        Answer = "India"
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
                    flag.setImageResource(R.drawable.indonesia)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option11[i]
                        Answer = "Indonesia"
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

                    flag.setImageResource(R.drawable.iran)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option12[i]
                        Answer = "Iran"
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
                    flag.setImageResource(R.drawable.iraq)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option13[i]
                        Answer = "Iraq"
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

                    flag.setImageResource(R.drawable.israel)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option14[i]
                        Answer = "Israel"
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
                    flag.setImageResource(R.drawable.japan)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option15[i]
                        Answer = "Japan"
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

                    flag.setImageResource(R.drawable.jordan)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option16[i]
                        Answer = "Jordan"
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
                    flag.setImageResource(R.drawable.kazakhstan)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option17[i]
                        Answer = "Kazakhstan"
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
                    flag.setImageResource(R.drawable.kuwait)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option18[i]
                        Answer = "Kuwait"
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

                    flag.setImageResource(R.drawable.kyrgyzstan)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option19[i]
                        Answer = "Kyrgyzstan"
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
                    flag.setImageResource(R.drawable.laos)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option20[i]
                        Answer = "Laos"
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

                    flag.setImageResource(R.drawable.lebanon)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option21[i]
                        Answer = "Lebanon"
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

                    flag.setImageResource(R.drawable.malaysia)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option22[i]
                        Answer = "Malaysia"
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
                    flag.setImageResource(R.drawable.maldives)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option23[i]
                        Answer = "Maldives"
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

                    flag.setImageResource(R.drawable.mongolia)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option24[i]
                        Answer = "Mongolia"
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
                    flag.setImageResource(R.drawable.nepal)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option25[i]
                        Answer = "Nepal"
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

                    flag.setImageResource(R.drawable.north_korea)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option26[i]
                        Answer = "N. Korea"
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
                    flag.setImageResource(R.drawable.oman)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option27[i]
                        Answer = "Oman"
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

                    flag.setImageResource(R.drawable.pakistan)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option28[i]
                        Answer = "Pakistan"
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
                    flag.setImageResource(R.drawable.palestine)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option29[i]
                        Answer = "Palestine"
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

                    flag.setImageResource(R.drawable.philippines)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option30[i]
                        Answer = "Philippines"
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
                    flag.setImageResource(R.drawable.qatar)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option31[i]
                        Answer = "Qatar"
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

                    flag.setImageResource(R.drawable.saudi_arabia)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option32[i]
                        Answer = "Saudi Arabia"
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
                    flag.setImageResource(R.drawable.singapore)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option33[i]
                        Answer = "Singapore"
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

                    flag.setImageResource(R.drawable.south_korea)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option34[i]
                        Answer = "S. Korea"
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
                    flag.setImageResource(R.drawable.sri_lanka)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option35[i]
                        Answer = "Sri Lanka"
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

                    flag.setImageResource(R.drawable.syria)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option36[i]
                        Answer = "Syria"
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
                    flag.setImageResource(R.drawable.tajikistan)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option37[i]
                        Answer = "Tajikistan"
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
                    flag.setImageResource(R.drawable.thailand)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option38[i]
                        Answer = "Thailand"
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

                    flag.setImageResource(R.drawable.turkey)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option39[i]
                        Answer = "Turkey"
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
                    flag.setImageResource(R.drawable.turkmenistan)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option40[i]
                        Answer = "Turkmenistan"
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

                    flag.setImageResource(R.drawable.uae)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option41[i]
                        Answer = "U.A.E"
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

                    flag.setImageResource(R.drawable.uzbekistan)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option42[i]
                        Answer = "Uzbekistan"
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
                    flag.setImageResource(R.drawable.vietnam)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option43[i]
                        Answer = "Vietnam"
                    }
                    test++

                }
                43 -> {
                    val selected43 = group.checkedRadioButtonId
                    option = findViewById<View>(selected43) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }

                    flag.setImageResource(R.drawable.yemen)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option44[i]
                        Answer = "Yemen"
                    }
                    test++

                }
                44 -> {

                    val selected44 = group.checkedRadioButtonId
                    option = findViewById<View>(selected44) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    if (c < 10) {
                        val builder: AlertDialog.Builder = AlertDialog.Builder(this@Asia)
                        builder.setTitle("SCORE")
                        builder.setMessage("Your score is low, Please try again!")
                        builder.setCancelable(false)
                        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
                            val act2 = Intent(this, Asia::class.java)
                            startActivity(act2)
                            dialog.cancel()
                            finish()
                        })
                        builder.create().show()
                    } else {
                        Africa.score = Africa.score + c * 3 - w
                        if (HScore < Africa.score) {
                            HScore = Africa.score
                            val builder: AlertDialog.Builder = AlertDialog.Builder(this@Asia)
                            builder.setTitle("SCORE")
                            builder.setMessage(" New High Score!!! Your score is:${Africa.score}")
                            builder.setCancelable(false)
                            builder.setPositiveButton("Continue", DialogInterface.OnClickListener { dialog, id ->
                                val act2 = Intent(this, Europe::class.java)
                                startActivity(act2)
                                dialog.cancel()
                                finish()
                            })
                            builder.create().show()
                        } else {
                            val builder: AlertDialog.Builder = AlertDialog.Builder(this@Asia)
                            builder.setTitle("SCORE")
                            builder.setMessage("Your score is:" + Africa.score)
                            builder.setCancelable(false)
                            builder.setPositiveButton("Continue", DialogInterface.OnClickListener { dialog, id ->
                                val act2 = Intent(this, Europe::class.java)
                                startActivity(act2)
                                dialog.cancel()
                                finish()
                            })
                            builder.create().show()
                        }
                    }
                    flag.setImageResource(R.drawable.asia)
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