package com.example.shopsavvycommvp.util.extension

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.res.Resources
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shopsavvycommvp.R
import com.google.android.material.tabs.TabLayout
import java.util.*

/**
 * @param font fontID
 * @sample: yourTextView.setFont(R.font.noto_sans_bold)
 */
fun TextView.setFont(font: Int) {
    val typeface = ResourcesCompat.getFont(context, font)
    this.typeface = typeface
}

/**
 * Visible/Invisible the view
 * @param visible is visible
 * @param goneIfInvi the view is gone if true
 */
fun View.visible(visible: Boolean = true, goneIfInvi: Boolean = true) {
    if (visible) {
        this.visibility = View.VISIBLE
    } else {
        if (goneIfInvi) {
            this.visibility = View.GONE
        } else {
            this.visibility = View.INVISIBLE
        }
    }
}

/**
 * Visible the view
 */
fun View.visible() {
    this.visibility = View.VISIBLE
}

/**
 * Invisible the view
 */
fun View.invisible() {
    this.visibility = View.INVISIBLE
}

/**
 * Gone the view
 */
fun View.gone() {
    this.visibility = View.GONE
}

/**
 * Hide keyboard
 */
fun View.hideKeyboard() {
    val inputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}

/**
 * Remove padding tablayout item
 */
fun TabLayout.removePaddingTabItem() {
    for (i in 0 until tabCount) {
        val customView = getTabAt(i)?.customView
        val tabParent = customView?.parent as LinearLayout?
        val param = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        tabParent?.layoutParams = param
        tabParent?.setPadding(0, 0, 0, 0)
    }
}
fun Activity.hideSoftKeyboard() {
    currentFocus?.let {
        val inputMethodManager = ContextCompat.getSystemService(this, InputMethodManager::class.java)!!
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}
/**
 * @param text the string of the TextView
 * @param start spannable start index
 * @param end spannable end index
 * @param onClick onClick listener
 * @param color span color
 * @param isUnderlineText underline span
 * @sample:
 * val text = getString(R.string.string_id)
 * yourTextView.setClickableSpan(text, 52, text.length, {})
 */
fun TextView.setClickableSpan(text: String, start: Int, end: Int, onClick: (View) -> Unit, color: Int = Color.WHITE, isUnderlineText: Boolean = true) {
    val clickableSpan = object : ClickableSpan() {
        override fun onClick(textView: View) {
            onClick.invoke(textView)
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.isUnderlineText = isUnderlineText
            ds.color = color
        }
    }
    val spannableString = SpannableString(text)
    spannableString.setSpan(clickableSpan, start, end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
    this.text = spannableString
    this.movementMethod = LinkMovementMethod.getInstance()
    this.highlightColor = Color.TRANSPARENT
}

/**
 * Text color span will be added to textView
 * @param addedText the text that added to current TextView
 * @param position the index of text that added to TextView
 * @param colorRes span color
 * @exception Resources.NotFoundException
 * @sample: tv.addTextColorSpan(" *", tv.length(), R.color.red_dark)
 */
fun TextView.addTextColorSpan(addedText: String, position: Int = this.length(), colorRes: Int = R.color.red_dark) {
    try {
        val builder = StringBuilder(text.toString())
        builder.insert(position, addedText)
        val spannableString = SpannableString(builder.toString())
        val span = ForegroundColorSpan(ContextCompat.getColor(context, colorRes))
        spannableString.setSpan(span, position, position + addedText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        text = spannableString
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}

/**
 * Set text color span
 * @param start start index
 * @param end end index exclusive
 * @param colorRes color resource
 * @param fontRes font resource
 * @exception Resources.NotFoundException
 */
fun TextView.setTextColorSpan(start: Int, end: Int, colorRes: Int = R.color.red_dark, fontRes: Int = R.font.noto_sans_regular) {
    try {
        val font = ResourcesCompat.getFont(context, fontRes) ?: return
        val color = ContextCompat.getColor(context, colorRes)
        val spannableString = SpannableString(text)
        spannableString.setSpan(StyleSpan(font.style), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        text = spannableString
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}




/**
 * Inflate item view
 */
fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}



/**
 * find view by id
 * @param id view id
 */
fun RecyclerView.ViewHolder.findView(id: Int): View {
    return itemView.findViewById(id)
}

/**
 * get color from view context
 */
fun View.getColor(resId: Int): Int {
    return ContextCompat.getColor(context, resId)
}

/**
 * get string from view context
 */
fun View.getString(resId: Int?): String {
    return if (resId == null) {
        ""
    } else {
        context.getString(resId)
    }
}