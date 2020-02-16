package com.yuvasai.baseproject.repository

import android.content.Context
import com.igweze.ebi.simplecalladapter.Simple
import com.yuvasai.baseproject.server.apis.Api
import com.yuvasai.baseproject.server.response.ArticlesResponse
import com.yuvasai.baseproject.server.response.ForecastResponse
import com.yuvasai.baseproject.server.response.WeatherResponse
import javax.inject.Inject

class Repository @Inject constructor(
    val context: Context,
    private val api: Api,
    private val localDataRepository: ILocalDataRepository
) : IRepository {

    override fun getWeather(): Simple<WeatherResponse> {
        return api.getWeather()
    }

    override fun getForecast(): Simple<ForecastResponse> {
        return api.getForecast()
    }
}