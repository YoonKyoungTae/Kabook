package dev.androidblog.kabook.util

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.text.NumberFormat

fun ImageView.simpleLoadUrl(url: String) {
    if (url.isNotEmpty()) {
        Glide.with(this)
            .load(url)
            .into(this)
    }

}

fun String.toYearMonth(): String {
    val year = this.substring(0, 4)
    val month = this.substring(5, 7)
    return "${year}년 ${month}월"
}

fun Int.toNumber(): String {
    return NumberFormat.getIntegerInstance().format(this)
}

fun Long.toNumber(): String {
    return NumberFormat.getIntegerInstance().format(this)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}
