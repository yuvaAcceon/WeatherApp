package com.yuvasai.baseproject.ui

import android.annotation.SuppressLint
import android.content.Context
import androidx.databinding.ObservableField
import com.igweze.ebi.simplecalladapter.ServerResponseException
import com.yuvasai.baseproject.base.BaseViewModel
import com.yuvasai.baseproject.base.MainArguments
import com.yuvasai.baseproject.repository.IRepository
import com.yuvasai.baseproject.server.response.Forecast
import javax.inject.Inject
import javax.inject.Named

class MainViewModel @Inject constructor(
    val context: Context,
//    @Named("arguments") var arguments: MainArguments,
    var repository: IRepository
) : BaseViewModel() {

    var temerature = ObservableField("")
    var forecastList: ArrayList<Forecast>? = null

    @SuppressLint("DefaultLocale")
    fun callApi() {
        repository.getWeather().process(
            onSuccess = {
                println("==============weather_success: temp=${it?.main?.temp ?: 0} =================")
                temerature.set(
                    java.lang.String.format(
                        "%.2f",
                        ((it?.main?.temp ?: 0.0) - 275.15)
                    ) + " C"
                )
            },
            onFailure = {
                handleError(it, callback = fun(_: String?) {
                    when (it) {
                        else -> {
                            println("==============weather_failure=================")
                        }
                    }
                })
            }
        )
    }

    @SuppressLint("DefaultLocale")
    fun callForecastApi() {
        repository.getForecast().process(
            onSuccess = {
                println("============== forecast_success =================")
                if (it?.forecast != null) {
                    forecastList = it.forecast as ArrayList<Forecast>
                }
            },
            onFailure = {
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
}