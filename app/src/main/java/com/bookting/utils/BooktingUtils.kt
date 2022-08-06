package com.bookting.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.util.TypedValue
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import java.text.SimpleDateFormat
import java.util.*

fun Float.dpToPx(context: Context): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        context.resources.displayMetrics
    )
}

fun Activity.setStatusTrans() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
        window.apply {
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.TRANSPARENT
        }
    } else {
        val controller = window.insetsController
        controller?.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
        controller?.systemBarsBehavior =
            WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        window.setDecorFitsSystemWindows(false)
    }
}

fun showToast(context: Context, toast: String) {
    Toast.makeText(context, toast, Toast.LENGTH_LONG).show()
}

fun Activity.setFullScreen() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val controller = window.insetsController
        controller?.let {
            it.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    } else {
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}

fun ImageView.setImg(uri: Any) {
    Glide.with(this).load(uri).into(this)
}

fun ImageView.setRoundImg(uri: Any) {
    Glide.with(this).load(uri).apply(RequestOptions.bitmapTransform(RoundedCorners(10))).into(this)
}

fun getDayOfTime(yyyyMMdd: String): String {
    val formatter = SimpleDateFormat("yyyyMMdd", Locale.KOREA)
    val d = formatter.parse(yyyyMMdd)
    val dayFormat = SimpleDateFormat("E", Locale.KOREA)
    return dayFormat.format(d!!)
}

fun dpToPx(resource: Resources, size: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, resource.displayMetrics)
}

fun getCurrentTime(): String {
    val format = SimpleDateFormat("yyyyMMdd", Locale.KOREA)
    return format.format(System.currentTimeMillis())
}