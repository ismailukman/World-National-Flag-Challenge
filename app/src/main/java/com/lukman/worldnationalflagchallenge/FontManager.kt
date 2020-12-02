package com.lukman.worldnationalflagchallenge

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class FontManager {

    val FONTAWESOME = "font/" + "fasolid900.ttf"
    //val typeface = Typeface.createFromAsset(assets, "fasolid900.ttf");

    fun getTypeface(context: Context, font: String?): Typeface? {
        return Typeface.createFromAsset(context.assets, font)
    }

    fun markAsIconContainer(v: View, typeface: Typeface?) {
        if (v is ViewGroup) {
            for (i in 0 until v.childCount) {
                val child: View = v.getChildAt(i)
                markAsIconContainer(child, typeface)
            }
        } else if (v is TextView) {
            v.typeface = typeface
        }
    }

    //yourTextView.setTypeface(FontManager.getTypeface(FontManager.YOURFONT));

}