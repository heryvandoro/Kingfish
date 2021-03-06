package com.directdev.portal.utils

import android.content.Context
import android.net.ConnectivityManager
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.KeyEvent
import android.view.View
import org.jetbrains.anko.onKey

fun Any.savePref(ctx: Context, @StringRes id: Int) {
    val key = ctx.getString(id)
    val editor = ctx.getSharedPreferences("com.kingfish", Context.MODE_PRIVATE).edit()
    when (this) {
        is String -> editor.putString(key, this)
        is Boolean -> editor.putBoolean(key, this)
        is Float -> editor.putFloat(key, this)
        is Int -> editor.putInt(key, this)
        is Long -> editor.putLong(key, this)
        else -> return
    }
    editor.commit()
}

fun Context.readPref(@StringRes id: Int, defaultValue: Any, preferenceId: String = "com.kingfish"): Any {
    val sp = this.getSharedPreferences(preferenceId, Context.MODE_PRIVATE)
    val key = this.getString(id)
    return when (defaultValue) {
        is String -> sp.getString(key, defaultValue)
        is Boolean -> sp.getBoolean(key, defaultValue)
        is Float -> sp.getFloat(key, defaultValue)
        is Int -> sp.getInt(key, defaultValue)
        is Long -> sp.getLong(key, defaultValue)
        else -> 0
    }
}

fun Context.clearPref() {
    val editor = getSharedPreferences("com.kingfish", Context.MODE_PRIVATE).edit()
    editor.clear()
    editor.commit()
}

fun View.snack(msg: Any, length: Int = Snackbar.LENGTH_SHORT, option: Snackbar.() -> Unit = {}) {
    val snack = Snackbar.make(this, msg.toString(), length)
    snack.option()
    snack.show()
}

fun View.onEnter(callback: () -> Unit) {
    onKey { view, i, keyEvent ->
        if (keyEvent?.action == KeyEvent.ACTION_DOWN &&
                keyEvent?.keyCode == KeyEvent.KEYCODE_ENTER)
            callback()
        false
    }
}

fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(color) }
}

fun ConnectivityManager.isNetworkAvailable(): Boolean {
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}