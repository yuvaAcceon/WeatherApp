package com.yuvasai.baseproject.server.apis

import com.igweze.ebi.simplecalladapter.Simple
import com.yuvasai.baseproject.server.response.ArticlesResponse
import com.yuvasai.baseproject.server.response.BaseResponse
import com.yuvasai.baseproject.server.response.ForecastResponse
import com.yuvasai.baseproject.server.response.WeatherResponse
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @GET("weather?q=hyderabad,india&APPID=17c6df7c9c9b8a3404f39d32af2bec6e")
    fun getWeather(): Simple<WeatherResponse>

    @GET("forecast/daily?mode=json&q=hyderabad&lang=english&cnt=5&APPID=17c6df7c9c9b8a3404f39d32af2bec6e")
    fun getForecast(): Simple<ForecastResponse>
}