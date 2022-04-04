package com.base.common_android.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.isGone(): Boolean = visibility == View.GONE

fun View.isVisible(): Boolean = visibility == View.VISIBLE

fun View.isInvisible(): Boolean = visibility == View.INVISIBLE

fun View.visibleWhen(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE
}

fun View.goneWhen(condition: Boolean) {
    visibility = if (condition) View.GONE else View.VISIBLE
}

fun View.invisibleWhen(condition: Boolean) {
    visibility = if (condition) View.INVISIBLE else View.VISIBLE
}

fun View.enable() {
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}

fun View.showEnabled() {
    isEnabled = true
    appearEnabledWhen(true)
}

fun View.showDisabled() {
    isEnabled = false
    appearEnabledWhen(false)
}

fun SwitchCompat.checkWhen(condition: Boolean) {
    isChecked = condition
}

fun View.enableWhen(condition: Boolean) {
    isEnabled = condition
    appearEnabledWhen(condition)
}

fun View.appearEnabledWhen(condition: Boolean) {
    alpha = if (condition) 1f else .5f
}

fun TextView.showText(text: CharSequence) {
    this.text = text
    this.visibility = View.VISIBLE
}

fun TextView.showTextOrGone(text: CharSequence?) {
    if (text?.isNotBlank() == true) {
        this.text = text
        visible()
    } else {
        clearTextAndGone()
    }
}

fun TextView.showTextOrHide(text: CharSequence?) {
    if (text?.isNotBlank() == true) {
        this.text = text
        visible()
    } else {
        clearTextAndHide()
    }
}

fun TextView.clearText() {
    text = null
}

fun TextView.clearTextAndGone() {
    clearText()
    gone()
}

fun TextView.clearTextAndHide() {
    clearText()
    invisible()
}

fun ImageView.showImage(resId: Int) {
    this.setImageResource(resId)
    this.visibility = View.VISIBLE
}
