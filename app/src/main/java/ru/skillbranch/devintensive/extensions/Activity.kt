package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val inputManager: InputMethodManager =
        this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(
        this.currentFocus?.windowToken,
        InputMethodManager.HIDE_NOT_ALWAYS
    )
}

fun Activity.isKeyboardOpen(): Boolean {
    val r = Rect()
    val rootView = this.window.decorView
    rootView.getWindowVisibleDisplayFrame(r)
    val screenHeight: Int = rootView.height
    val heightDifference = screenHeight - (r.bottom - r.top)
    return heightDifference > 100
}

fun Activity.isKeyboardClosed(): Boolean {
    val r = Rect()
    val rootView = this.window.decorView
    rootView.getWindowVisibleDisplayFrame(r)
    val screenHeight: Int = rootView.height
    val heightDifference = screenHeight - (r.bottom - r.top)
    return heightDifference == 0
}