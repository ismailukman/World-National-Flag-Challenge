package com.lukman.worldnationalflagchallenge

import android.app.AlertDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.widget.*
var Lmode = ""
class About : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        var love = findViewById<TextView>(R.id.by)

        love.setOnLongClickListener(){
            askAuth()
            true
        }

    }

    private fun dedication(){
        val builder = AlertDialog.Builder(this@About)
        builder.setIcon(R.drawable.like)
        // Set the alert dialog title
        builder.setTitle("Dedicated to my Wife")
        val view = layoutInflater.inflate(R.layout.love, null)
        // Display a message on alert dialog
/*        builder.setMessage("I dedicate this game application to my lovely wife, the Beauty of All Beauty. May garden of joy and happiness be yours. You mean a lot to me my Love.\n" +
                "I am happy that this finally worked at least closer to what i want. ")*/

        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton("Close"){dialog, which ->
            // Do something when user press the positive button
            //Toast.makeText(applicationContext,"I love my Wife", Toast.LENGTH_SHORT).show()
        }

        builder.setView(view)

        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()
        dialog.setCanceledOnTouchOutside(true)
        dialog.show()
    }

    private fun askAuth() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle("Is this message for you?")
        builder.setMessage("Please enter your password")
        val editText = EditText(this)
        var password = ""
        var lp =  LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        editText.layoutParams = lp
        editText.hint = "enter anything"
        editText.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        editText.transformationMethod = PasswordTransformationMethod.getInstance();

        builder.setPositiveButton("DONE") { dialog, which ->
            password = editText.text.toString()

            if( password == "54321" ){
                Toast.makeText(applicationContext,"Thank you my Love", Toast.LENGTH_SHORT).show()
                dedication()
            }else{
                Toast.makeText(applicationContext,"Thank you", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }
        builder.setNegativeButton("CANCEL") { dialog, which ->
            // Do something
            //val intent = Intent(this, AppList::class.java)
            //startActivity(intent)
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.setView(editText)
        alert.show()
        //alert.window?.setBackgroundDrawable(ColorDrawable(Color.GREEN));
    }


}
