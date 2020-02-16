package com.yuvasai.baseproject.base

import android.view.View
import android.view.animation.AnimationUtils
import androidx.databinding.BindingAdapter
import com.yuvasai.baseproject.R

object ViewDataBinder {

    @JvmStatic
    @BindingAdapter("slideUp")
    fun bindSlideUpAnimation(view: View, slideUp: Boolean) {
        if (slideUp) {
            view.startAnimation(AnimationUtils.loadAnimation(view.context, R.anim.slide_up))
        }else{
            view.startAnimation(AnimationUtils.loadAnimation(view.context, R.anim.slide_down))
        }
    }
}