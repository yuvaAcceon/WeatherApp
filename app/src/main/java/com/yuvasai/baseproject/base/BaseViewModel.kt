package com.yuvasai.baseproject.base

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseViewModel : ViewModel() {

    lateinit var navigatorListener: Navigator
    val systemAlertListener = ObservableField(ErrorTemplate.NONE)


    fun route(navigateTo: String, data: Any? = null) {
        this.navigatorListener.navigate(navigateTo, data)
    }

    protected fun handleError(throwable: Throwable?, callback: (message: String?) -> Unit) {
        var message = when (throwable) {
            is HttpException -> {
                throwable.message()
            }
            is SocketTimeoutException -> {
                throwable.localizedMessage
            }
            is UnknownHostException -> {
                systemAlertListener.set(ErrorTemplate.NO_INTERNET)
                null
            }
            else -> {
                "Unknown"
            }
        }
        if (!message.isNullOrEmpty()) {
            callback(message)
        }
        Log.d(this.javaClass.canonicalName, "Error=$message")
    }

}