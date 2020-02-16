package com.yuvasai.baseproject.repository

import com.igweze.ebi.simplecalladapter.Simple
import com.yuvasai.baseproject.server.response.ArticlesResponse
import com.yuvasai.baseproject.server.response.ForecastResponse
import com.yuvasai.baseproject.server.response.WeatherResponse

interface IRepository {

    fun getWeather(): Simple<WeatherResponse>

    fun getForecast(): Simple<ForecastResponse>
}