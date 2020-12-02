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


class SAmerica : AppCompatActivity() {
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
        setContentView(R.layout.activity_s_america)

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
        for (i in 0..12) {
            rndm.add(i)
        }
        rndm.shuffle()

        x = rndm[0]
        //Toast.makeText(this, ""+rndm, Toast.LENGTH_SHORT).show()
        a = rndm.indexOf(0) // Returns index of first occurrence of 0
        rndm[0] = rndm[a] //rndm.get(10) Returns the object at index 10.
        rndm[a] = x

        x = rndm[12]
        b = rndm.indexOf(12)
        rndm[12] = rndm[b]
        rndm[b] = x
        hscimg.typeface = fontawesone


        val option1 = arrayOf("Ecuador", "Argentina", "Chile", "Brazil") //Argentina
        val option2 = arrayOf("Bolivia", "Peru", "Colombia", "Paraguay") //Bolivia
        val option3 = arrayOf("Chile", "Uruguay", "Brazil", "Venezuela") //Brazil
        val option4 = arrayOf("Chile", "Falkland Islands", "Peru", "Guyana") //Chile
        val option5 = arrayOf("Peru", "Colombia", "Argentina", "Guyana") //Colombia
        val option6 = arrayOf("Chile", "Uruguay", "Venezuela", "Ecuador") //Ecuador
        val option7 = arrayOf("Falkland Islands", "Brazil", "Bolivia", "Uruguay") //Falkland Islands
        val option8 = arrayOf("Ecuador", "Peru", "Guyana", "Colombia") //Guyana
        val option9 = arrayOf("Chile", "Paraguay", "Brazil", "Uruguay") //Paraguay
        val option10 = arrayOf("Ecuador", "Brazil", "Venezuela", "Peru") //Peru
        val option11 = arrayOf("Bolivia", "Guyana", "Uruguay", "Chile") //Uruguay
        val option12 = arrayOf("Venezuela", "Argentina", "Ecuador", "Guyana") //Venezuela


        btn.setOnClickListener {
            show.visibility =  View.GONE
            quiz.visibility =  View.VISIBLE

            flag.setImageResource(R.drawable.argentina);
            for (i in 0 until group.childCount) {
                (group.getChildAt(i) as RadioButton).text = option1[i]
                Answer = "Argentina"
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

                    flag.setImageResource(R.drawable.bolivia)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option2[i]
                        Answer = "Bolivia"
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
                    flag.setImageResource(R.drawable.brazil)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option3[i]
                        Answer = "Brazil"
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

                    flag.setImageResource(R.drawable.chile)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option4[i]
                        Answer = "Chile"
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
                    flag.setImageResource(R.drawable.colombia)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option5[i]
                        Answer = "Colombia"
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

                    flag.setImageResource(R.drawable.ecuador)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option6[i]
                        Answer = "Ecuador"
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
                    flag.setImageResource(R.drawable.falkland_islands)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option7[i]
                        Answer = "Falkland Islands"
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

                    flag.setImageResource(R.drawable.guyana)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option8[i]
                        Answer = "Guyana"
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
                    flag.setImageResource(R.drawable.paraguay)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option9[i]
                        Answer = "Paraguay"
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

                    flag.setImageResource(R.drawable.peru)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option10[i]
                        Answer = "Peru"
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
                    flag.setImageResource(R.drawable.uruguay)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option11[i]
                        Answer = "Uruguay"
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

                    flag.setImageResource(R.drawable.venezuela)
                    for (i in 0 until group.childCount) {
                        (group.getChildAt(i) as RadioButton).text = option12[i]
                        Answer = "Venezuela"
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
                    Africa.score = Africa.score + c * 3 - w
                    if (HScore < Africa.score) {
                        HScore = Africa.score
                        val builder: AlertDialog.Builder = AlertDialog.Builder(this@SAmerica)
                        builder.setTitle("SCORE")
                        builder.setMessage("New High Score!!!  Your score is: ${Africa.score}")
                        builder.setCancelable(false)
                        builder.setPositiveButton("Restart", DialogInterface.OnClickListener { dialog, id ->
                            val act2 = Intent(this, Africa::class.java)
                            startActivity(act2)
                            dialog.cancel()
                            finish()
                        })
                        builder.setNegativeButton("Exit", DialogInterface.OnClickListener { dialog, id ->
                            dialog.cancel()
                            Africa.score = 0
                            finish()
                        })
                        builder.create().show()
                    } else {
                        val builder: AlertDialog.Builder = AlertDialog.Builder(this@SAmerica)
                        builder.setTitle("SCORE")
                        builder.setMessage("Your score is:" + Africa.score)
                        builder.setCancelable(false)
                        builder.setPositiveButton("Restart", DialogInterface.OnClickListener { dialog, id ->
                            val act2 = Intent(this, Africa::class.java)
                            startActivity(act2)
                            dialog.cancel()
                            finish()
                        })
                        builder.setNegativeButton("Exit", DialogInterface.OnClickListener { dialog, id ->
                            dialog.cancel()
                            Africa.score = 0
                            finish()
                        })
                        builder.create().show()
                    }
                    flag.setImageResource(R.drawable.samerica)
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