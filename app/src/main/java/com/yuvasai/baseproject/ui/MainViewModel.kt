package com.yuvasai.baseproject.ui

import android.annotation.SuppressLint
import android.content.Context
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.yuvasai.baseproject.R
import com.yuvasai.baseproject.base.BaseViewModel
import com.yuvasai.baseproject.repository.IRepository
import com.yuvasai.baseproject.server.response.Forecast
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class MainViewModel @Inject constructor(
    val context: Context,
//    @Named("arguments") var arguments: MainArguments,
    var repository: IRepository
) : BaseViewModel() {

    var temperature = ObservableField("")
    var forecastList: ArrayList<Forecast>? = null
    var weatherStatus = ObservableField(false)
    var forecastStatus = ObservableField(false)

    @SuppressLint("DefaultLocale")
    fun callApi() {
        showDefaultLoader()
        repository.getWeather().process(
            onSuccess = {
                dismissDefaultLoader()
                weatherStatus.set(true)
                println(
                    "==============weather_success: temp=${it?.main?.temp ?: 0} ================="
                )
                temperature.set(
                    java.lang.String.format(
                        "%.2f",
                        ((it?.main?.temp ?: 0.0) - 275.15)
                    ) + " C"
                )
            },
            onFailure = {
                dismissDefaultLoader()
                weatherStatus.set(false)
                handleError(it, callback = fun(_: String?) {
                    when (it) {
                        else -> {
                            println("==============weather_failure=================")
                            temperature.set("")
                        }
                    }
                })
            }
        )
    }

    @SuppressLint("DefaultLocale")
    fun callForecastApi() {
        forecastList = null
        repository.getForecast().process(
            onSuccess = {
                println("============== forecast_success =================")
                forecastStatus.set(true)
                if (it?.forecast != null) {
                    forecastList = it.forecast as ArrayList<Forecast>
                }
            },
            onFailure = {
                forecastStatus.set(false)
                handleError(it, callback = fun(_: String?) {
                    when (it) {
                        else -> {
                            println("==============forecast_failure=================")
                        }
                    }
                })
            }
        )
    }

    @SuppressLint("DefaultLocale")
    fun callForecastApi2() {
        showDefaultLoader()
        forecastList=null
        repository.getForecast().process(
            onSuccess = {
                dismissDefaultLoader()
                println("============== forecast_success =================")
                forecastStatus.set(true)
                if (it?.forecast != null) {
                    forecastList = it.forecast as ArrayList<Forecast>
                }
            },
            onFailure = {
                dismissDefaultLoader()
                forecastStatus.set(false)
                handleError(it, callback = fun(_: String?) {
                    when (it) {
                        else -> {
                            println("==============forecast_failure=================")
                        }
                    }
                })
            }
        )
    }

    @SuppressLint("DefaultLocale")
    fun getTemperatureFromForecast(number: Int): String {
        return if (forecastList != null) {
            java.lang.String.format(
                "%.2f",
                ((forecastList?.get(number)?.temperature?.value?.toDouble() ?: 0.0) - 275.15)
            ) + " C"
        } else {
            ""
        }

    }

    @SuppressLint("SimpleDateFormat")
    fun getCurrentTemperature(): String {
        var sdf = SimpleDateFormat("dd MMM, yyyy")
        return context.getString(R.string.label_current_temperature) + "(${sdf.format(Date())}):"
    }

    @SuppressLint("SimpleDateFormat")
    fun getNextDateAfter(number: Int): String {
        var sdf = SimpleDateFormat("dd MMM, yyyy")
        var calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, number)
        return sdf.format(calendar.time)
    }
}