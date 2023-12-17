package com.testvass.rickandmorty.ui.utils

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewAnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.graphics.drawable.DrawableCompat
import coil.load
import coil.request.CachePolicy
import com.google.android.material.snackbar.Snackbar
import com.testvass.rickandmorty.R
import com.testvass.rickandmorty.ui.utils.Constants.TAG_DATE_FORMAT_IN
import com.testvass.rickandmorty.ui.utils.Constants.TAG_DATE_FORMAT_OUT
import com.testvass.rickandmorty.ui.utils.Constants.TAG_DATE_FORMAT_OUT_SHORT
import com.testvass.rickandmorty.ui.utils.Constants.TAG_DATE_UNKNOWN
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.atomic.AtomicBoolean

fun View.showOrHide(show: Boolean) {
    visibility = if (show) View.VISIBLE
    else View.INVISIBLE
}

fun View.showOrDisappear(show: Boolean) {
    visibility = if (show) View.VISIBLE
    else View.GONE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.disappear() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hideKeyboard() {
    val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(windowToken, 0)
}

fun View.showSnackBar(message: String) {
    val snack = Snackbar.make(this, message, 5000)
    val snackView = snack.view
    snackView.setBackgroundColor(Color.BLACK)
    snackView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).maxLines = 5
    snack.show()
}

fun View.circularRevealedAtCenter(color: String) {
    val cx = 0
    val cy = left
    val finalRadius = kotlin.math.hypot(width.toDouble(), height.toDouble())
    if (isAttachedToWindow) {
        ViewAnimationUtils.createCircularReveal(this, cx, cy, 0f, finalRadius.toFloat()).apply {
            DrawableCompat.setTint(background, Color.parseColor(color))
            show()
            duration = 550
            start()
        }
    }
}

fun View.setOnSingleClickListener(clickListener: ((View) -> Unit)?) {
    clickListener?.also {
        setOnClickListener(
            OnSingleClickListener(
                it
            )
        )
    } ?: setOnClickListener(null)
}

class OnSingleClickListener(
    private val clickListener: (View) -> Unit,
    private val intervalMs: Long = 1000
) : View.OnClickListener {

    private var canClick = AtomicBoolean(true)

    override fun onClick(v: View?) {
        if (canClick.getAndSet(false)) {
            v?.run {
                postDelayed({
                    canClick.set(true)
                }, intervalMs)
                clickListener.invoke(v)
            }
        }
    }
}

fun AppCompatImageView.loadImage(url: String, crossFade: Boolean = true) {
    this.load(url) {
        memoryCachePolicy(CachePolicy.ENABLED)
        diskCachePolicy(CachePolicy.ENABLED)
        crossfade(crossFade)
        memoryCacheKey(url)
        placeholder(R.drawable.ic_launcher)
        error(R.drawable.ic_launcher)
        fallback(R.drawable.ic_launcher)
    }
}

fun String?.toHumanDate(): String {
    return this?.let {
        val formatIn = SimpleDateFormat(TAG_DATE_FORMAT_IN, Locale.US)
        val formatOut = SimpleDateFormat(TAG_DATE_FORMAT_OUT, Locale.US)
        val date = formatIn.parse(this)
        date?.let {
            formatOut.format(date)
        } ?: TAG_DATE_UNKNOWN
    } ?: TAG_DATE_UNKNOWN
}

fun String?.toHumanDateShort(): String {
    return this?.let {
        val formatIn = SimpleDateFormat(TAG_DATE_FORMAT_IN, Locale.US)
        val formatOut = SimpleDateFormat(TAG_DATE_FORMAT_OUT_SHORT, Locale.US)
        val date = formatIn.parse(this.orEmpty())
        date?.let {
            formatOut.format(date)
        } ?: TAG_DATE_UNKNOWN
    } ?: TAG_DATE_UNKNOWN
}

object Constants {
    const val TAG_DATE_FORMAT_IN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val TAG_DATE_FORMAT_OUT = "EEEE dd 'of' MMMM 'of' yyyy"
    const val TAG_DATE_FORMAT_OUT_SHORT = "dd 'of' MMMM 'of' yyyy"
    const val TAG_DATE_UNKNOWN = "Date: Unknown"
}