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

@Suppress("DEPRECATION")
class Africa : AppCompatActivity() {
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
    //private var PRIVATE_MODE = 0
    var Ans: String? = null; var Answer:kotlin.String? = null
    val PREFS_NAME = "MyPrefsFile"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_africa)
        show = findViewById(R.id.show)
        quiz = findViewById( R.id.quiz)

        show.visibility =  View.VISIBLE
        quiz.visibility =  View.GONE

        var settings = getSharedPreferences(PREFS_NAME, 0)
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
        for (i in 0..54) {
            rndm.add(i)
        }
        rndm.shuffle()

        x = rndm[0]
        //Toast.makeText(this, ""+rndm[test], Toast.LENGTH_SHORT).show()
        a = rndm.indexOf(0) // Returns index of first occurrence of 0
        rndm[0] = rndm[a] //rndm.get(10) Returns the object at index 10.
        rndm[a] = x

        x = rndm[54]
        b = rndm.indexOf(54)
        rndm[54] = rndm[b]
        rndm[b] = x
        hscimg.typeface = fontawesone

        val option1 = arrayOf("Libya", "Algeria", "Egypt", "Morocco") //Algeria
        val option2 = arrayOf("Lesotho", "Chad", "Liberia", "Angola") //Angola
        val option3 = arrayOf("Benin", "Kenya", "E. Guinea", "Gabon") //Benin
        val option4 =   arrayOf("Ethiopia", "Angola", "Botswana", "Burkina Faso") //Botswana
        val option5 = arrayOf( "Burkina Faso", "Cote d'Ivoire", "Rwanda", "Tanzania") //Burkina Faso
        val option6 =  arrayOf("Kenya", "Mauritania", "Burundi", "Mali") //Burundi
        val option7 = arrayOf("Niger", "Cameroon", "Djibouti", "D.R Congo") //Cameroon
        val option8 = arrayOf("Madagascar",  "Cape Verde",  "Guinea Bissau",  "Gabon"  ) //Cape Verde
        val option9 =  arrayOf("C. Africa Rep.", "Uganda", "Togo", "Zambia") //C. Africa Rep.
        val option10 =  arrayOf("Chad", "Burkina Faso", "Guinea", "Swaziland") //Chad
        val option11 =  arrayOf("Gambia", "Chad", "Nigeria", "Cote d'Ivoire") //Cote d'Ivoire
        val option12 =  arrayOf("Namibia", "Mauritius", "D.R Congo", "Djibouti") //D.R Congo
        val option13 =  arrayOf("Eritrea", "Djibouti", "Ethiopia", "Mozambique") //Djibouti
        val option14 =  arrayOf("Gabon", "Mali", "E. Guinea", "Egypt") //Egypt
        val option15 =  arrayOf("Lesotho", "E. Guinea", "Seychelles", "Reunion") //E. Guinea
        val option16 =  arrayOf("R. Congo", "South Africa", "Eritrea", "Tanzania") //Eritrea
        val option17 =  arrayOf("Sierra Leone", "Senegal", "Gabon", "Ethiopia") //Ethiopia
        val option18 =  arrayOf("Tunisia", "Rwanda", "Ghana", "Gabon") //Gabon
        val option19 =  arrayOf("Gambia", "Niger", "Reunion", "R. Congo") //Gambia
        val option20 =  arrayOf("E. Guinea", "Senegal", "Ghana", "Somalia") //Ghana
        val option21 = arrayOf("Mali", "Sierra Leone", "Guinea", "Tunisia") //Guinea
        val option22 = arrayOf( "Zambia", "Guinea Bissau", "Sao Tome & P.",  "Gabon") //Guinea Bissau
        val option23 =  arrayOf("Kenya", "Ethiopia", "Somalia", "Rwanda") //Kenya
        val option24 =  arrayOf("Swaziland", "Lesotho", "S. Sudan", "Reunion") //Lesotho
        val option25 =  arrayOf("Madagascar", "South Africa", "Libya", "Liberia") //Liberia
        val option26 =  arrayOf("Somalia", "Morocco", "Libya", "Niger") //Libya
        val option27 = arrayOf("Togo", "Swaziland", "Seychelles", "Madagascar") //Madagascar
        val option28 = arrayOf("Uganda", "Tanzania", "Malawi", "Zambia") //Malawi
        val option29 = arrayOf("Mali", "Sierra Leone", "Tunisia", "Burkina Faso") //Mali
        val option30 = arrayOf("Sudan", "Mauritania", "Egypt", "Nigeria") //Mauritania
        val option31 = arrayOf( "Zimbabwe", "South Africa", "Mozambique", "Mauritius" ) //Mauritius
        val option32 = arrayOf("Senegal", "Morocco", "Gambia", "Sudan") //Morocco
        val option33 = arrayOf("Mozambique", "Madagascar", "S. Sudan", "Reunion") //Mozambique
        val option34 = arrayOf("Togo", "Zambia", "Tanzania", "Namibia") //Namibia
        val option35 = arrayOf("Swaziland", "Tunisia", "Niger", "Togo") //Niger
        val option36 = arrayOf("Nigeria", "Reunion", "Somalia", "Cote d'Ivoire") //Nigeria
        val option37 = arrayOf("Mozambique", "R. Congo", "Gambia", "Uganda") //R. Congo
        val option38 = arrayOf("Reunion", "Rwanda", "Nigeria", "Togo") //Reunion
        val option39 = arrayOf("Tanzania", "Uganda", "Zimbabwe", "Rwanda") //Rwanda
        val option40 = arrayOf("Senegal", "Sao Tome & P.", "Ghana", "Tunisia") //Sao Tome & P.
        val option41 = arrayOf("Togo", "Senegal", "Reunion", "Sudan") //Senegal
        val option42 = arrayOf("Madagascar", "S. Sudan", "Seychelles", "R. Congo") //Seychelles
        val option43 = arrayOf("Zambia", "Liberia", "Nigeria", "Sierra Leone") //Sierra Leone
        val option44 = arrayOf("Niger", "Kenya", "Somalia", "Tanzania") //Somalia
        val option45 = arrayOf("Tunisia", "South Africa", "Uganda", "Togo") //South Africa
        val option46 = arrayOf("S. Sudan", "Botswana", "Gambia", "Sudan") //S. Sudan
        val option47 =  arrayOf("Sudan", "Togo", "Egypt", "S. Sudan") //Sudan
        val option48 =  arrayOf("Lesotho", "Swaziland", "Tanzania", "Niger") //Swaziland
        val option49 =  arrayOf("Tanzania", "Seychelles", "Uganda", "Swaziland") //Tanzania
        val option50 = arrayOf("Zambia", "Tunisia", "Gambia", "Togo") //Togo
        val option51 = arrayOf("Morocco", "Sudan", "Tunisia", "Saudi Arabia") //Tunisia
        val option52 = arrayOf("Namibia", "Gambia", "Uganda", "Zimbabwe") //Uganda
        val option53 = arrayOf("Niger", "Zambia", "Rwanda", "Gabon") //Zambia
        val option54 = arrayOf("Seychelles", "Zimbabwe", "Togo", "Algeria") //Zimbabwe

        btn.setOnClickListener {
            show.visibility =  View.GONE
            quiz.visibility =  View.VISIBLE
            flag.setImageResource(R.drawable.algeria)
            for (i in 0 until group.childCount) {
                (group.getChildAt(i) as RadioButton).text = option1[i]
                Answer = "Algeria"
            }
            test+=1
        }

        qbtn.setOnClickListener {
            /*show.visibility =  View.VISIBLE
            quiz.visibility =  View.GONE*/
            //test=3
            when (rndm[test]) {

                1 -> {

                    val selected1 = group.checkedRadioButtonId
                    option = findViewById<View>(selected1) as RadioButton
                    Ans = option.text as String?

                    if (Answer == Ans) {
                        c++
                        up.text = c.toString()
                    } else {
                        w++
                        down.text = w.toString()
                    }

                    flag.setImageResource(R.drawable.angola)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option2[i]
                        Answer = "Angola"
                    }
                    test++

                    //Toast.makeText(this, "$test", Toast.LENGTH_SHORT).show()
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
                    flag.setImageResource(R.drawable.benin)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option3[i]
                        Answer = "Benin"
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

                    flag.setImageResource(R.drawable.botswana)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option4[i]
                        Answer = "Botswana"
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
                    flag.setImageResource(R.drawable.burkina_faso)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option5[i]
                        Answer = "Burkina Faso"
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

                    flag.setImageResource(R.drawable.burundi)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option6[i]
                        Answer = "Burundi"
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
                    flag.setImageResource(R.drawable.cameroon)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option7[i]
                        Answer = "Cameroon"
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

                    flag.setImageResource(R.drawable.cape_verde)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option8[i]
                        Answer = "Cape Verde"
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
                    flag.setImageResource(R.drawable.central_african_republic)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option9[i]
                        Answer = "C. Africa Rep."
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

                    flag.setImageResource(R.drawable.chad)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option10[i]
                        Answer = "Chad"
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
                    flag.setImageResource(R.drawable.cte_divoire)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option11[i]
                        Answer = "Cote d'Ivoire"
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

                    flag.setImageResource(R.drawable.democratic_republic_of_the_congo)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option12[i]
                        Answer = "D.R Congo"
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
                    flag.setImageResource(R.drawable.djibouti)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option13[i]
                        Answer = "Djibouti"
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

                    flag.setImageResource(R.drawable.egypt)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option14[i]
                        Answer = "Egype"
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
                    flag.setImageResource(R.drawable.equatorial_guinea)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option15[i]
                        Answer = "E. Guinea"
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

                    flag.setImageResource(R.drawable.eritrea)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option16[i]
                        Answer = "Eritrea"
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
                    flag.setImageResource(R.drawable.ethiopia)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option17[i]
                        Answer = "Ethiopia"
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
                    flag.setImageResource(R.drawable.gabon)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option18[i]
                        Answer = "Gabon"
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

                    flag.setImageResource(R.drawable.gambia)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option19[i]
                        Answer = "Gambia"
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
                    flag.setImageResource(R.drawable.ghana)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option20[i]
                        Answer = "Ghana"
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

                    flag.setImageResource(R.drawable.guinea)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option21[i]
                        Answer = "Guinea"
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

                    flag.setImageResource(R.drawable.guinea_bissau)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option22[i]
                        Answer = "Guinea Bissau"
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
                    flag.setImageResource(R.drawable.kenya)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option23[i]
                        Answer = "Kenya"
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

                    flag.setImageResource(R.drawable.lesotho)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option24[i]
                        Answer = "Lesotho"
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
                    flag.setImageResource(R.drawable.liberia)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option25[i]
                        Answer = "Liberia"
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

                    flag.setImageResource(R.drawable.libya)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option26[i]
                        Answer = "Libya"
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
                    flag.setImageResource(R.drawable.madagascar)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option27[i]
                        Answer = "Madagascar"
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

                    flag.setImageResource(R.drawable.malawi)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option28[i]
                        Answer = "Malawi"
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
                    flag.setImageResource(R.drawable.mali)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option29[i]
                        Answer = "Mali"
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

                    flag.setImageResource(R.drawable.mauritania)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option30[i]
                        Answer = "Mauritania"
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
                    flag.setImageResource(R.drawable.mauritius)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option31[i]
                        Answer = "Mauritius"
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

                    flag.setImageResource(R.drawable.morocco)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option32[i]
                        Answer = "Morocco"
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
                    flag.setImageResource(R.drawable.mozambique)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option33[i]
                        Answer = "Mozambique"
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

                    flag.setImageResource(R.drawable.namibia)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option34[i]
                        Answer = "Namibia"
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
                    flag.setImageResource(R.drawable.niger)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option35[i]
                        Answer = "Niger"
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

                    flag.setImageResource(R.drawable.nigeria)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option36[i]
                        Answer = "Nigeria"
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
                    flag.setImageResource(R.drawable.republic_of_congo)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option37[i]
                        Answer = "R. Congo"
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
                    flag.setImageResource(R.drawable.runion)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option38[i]
                        Answer = "Reunion"
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

                    flag.setImageResource(R.drawable.rwanda)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option39[i]
                        Answer = "Rwanda"
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
                    flag.setImageResource(R.drawable.sao_tome_and_principe)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option40[i]
                        Answer = "Sao Tome & P."
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

                    flag.setImageResource(R.drawable.senegal)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option41[i]
                        Answer = "Senegal"
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

                    flag.setImageResource(R.drawable.seychelles)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option42[i]
                        Answer = "Seychelles"
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
                    flag.setImageResource(R.drawable.sierra_leone)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option43[i]
                        Answer = "Sierra Leone"
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

                    flag.setImageResource(R.drawable.somalia)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option44[i]
                        Answer = "Somalia"
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
                    flag.setImageResource(R.drawable.south_africa)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option45[i]
                        Answer = "South Africa"
                    }
                    test++

                }
                45 -> {
                    val selected45 = group.checkedRadioButtonId
                    option = findViewById<View>(selected45) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }

                    flag.setImageResource(R.drawable.south_sudan)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option46[i]
                        Answer = "S. Sudan"
                    }
                    test++

                }
                46 -> {
                    val selected46 = group.checkedRadioButtonId
                    option = findViewById<View>(selected46) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    flag.setImageResource(R.drawable.sudan)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option47[i]
                        Answer = "Sudan"
                    }
                    test++

                }
                47 -> {
                    val selected47 = group.checkedRadioButtonId
                    option = findViewById<View>(selected47) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }

                    flag.setImageResource(R.drawable.swaziland)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option48[i]
                        Answer = "Swaziland"
                    }
                    test++

                }
                48 -> {
                    val selected48 = group.checkedRadioButtonId
                    option = findViewById<View>(selected48) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    flag.setImageResource(R.drawable.tanzania)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option49[i]
                        Answer = "Tanzania"
                    }
                    test++

                }
                49 -> {
                    val selected49 = group.checkedRadioButtonId
                    option = findViewById<View>(selected49) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }

                    flag.setImageResource(R.drawable.togo)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option50[i]
                        Answer = "Togo"
                    }
                    test++

                }
                50 -> {
                    val selected50 = group.checkedRadioButtonId
                    option = findViewById<View>(selected50) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    flag.setImageResource(R.drawable.tunisia)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option51[i]
                        Answer = "Tunisia"
                    }
                    test++

                }
                51 -> {
                    val selected51 = group.checkedRadioButtonId
                    option = findViewById<View>(selected51) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }

                    flag.setImageResource(R.drawable.uganda)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option52[i]
                        Answer = "Uganda"
                    }
                    test++

                }
                52 -> {
                    val selected52 = group.checkedRadioButtonId
                    option = findViewById<View>(selected52) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    flag.setImageResource(R.drawable.zambia)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option53[i]
                        Answer = "Zambia"
                    }
                    test++

                }
                53 -> {
                    val selected53 = group.checkedRadioButtonId
                    option = findViewById<View>(selected53) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }

                    flag.setImageResource(R.drawable.zimbabwe)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option54[i]
                        Answer = "Zimbabwe"
                    }
                    test++

                }
                54 -> {
                    val selected54 = group.checkedRadioButtonId
                    option = findViewById<View>(selected54) as RadioButton
                    Ans = option.text as String

                    if (Answer == Ans) {
                        c++
                        up.text = Integer.toString(c)
                    } else {
                        w++
                        down.text = Integer.toString(w)
                    }
                    if (c < 15) {
                        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                        builder.setTitle("SCORE")
                        builder.setMessage("Your score is low, Please try again!")
                        builder.setCancelable(false)
                        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
                            val act2 = Intent(this, Africa::class.java)
                            startActivity(act2)
                            dialog.cancel()
                            finish()
                        })
                        builder.create().show()
                    } else {
                        score = c * 3 - w
                        if (HScore < score) {
                            HScore = score
                            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                            builder.setTitle("SCORE")
                            builder.setMessage("New High Score!!!\nYour score is:$score")
                            builder.setCancelable(false)
                            builder.setPositiveButton("Continue", DialogInterface.OnClickListener { dialog, id ->
                                val act2 = Intent(this, Asia::class.java)
                                startActivity(act2)
                                dialog.cancel()
                                finish()
                            })
                            builder.create().show()
                        } else {
                            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                            builder.setTitle("SCORE")
                            builder.setMessage("Your score is:$score")
                            builder.setCancelable(false)
                            builder.setPositiveButton("Continue", DialogInterface.OnClickListener { dialog, id ->
                                val act2 = Intent(this, Asia::class.java)
                                startActivity(act2)
                                dialog.cancel()
                                finish()
                            })
                            builder.create().show()
                        }
                    }
                    flag.setImageResource(R.drawable.africa)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option5[i]
                    }


                }
                else -> {

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