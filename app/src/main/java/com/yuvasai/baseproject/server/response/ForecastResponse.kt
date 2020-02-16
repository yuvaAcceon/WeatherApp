package com.yuvasai.baseproject.server.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ForecastResponse {

    @SerializedName("location")
    @Expose
    var location: Location? = null
    @SerializedName("credit")
    @Expose
    var credit: List<Any>? = null
    @SerializedName("meta")
    @Expose
    var meta: Meta? = null
    @SerializedName("sun")
    @Expose
    var sun: Sun? = null
    @SerializedName("forecast")
    @Expose
    var forecast: List<Forecast>? = null

}

class Clouds {

    @SerializedName("@value")
    @Expose
    var value: String? = null
    @SerializedName("@all")
    @Expose
    var all: String? = null
    @SerializedName("@unit")
    @Expose
    var unit: String? = null

}

class Forecast {

    @SerializedName("@from")
    @Expose
    var from: String? = null
    @SerializedName("@to")
    @Expose
    var to: String? = null
    @SerializedName("symbol")
    @Expose
    var symbol: Symbol? = null
    @SerializedName("precipitation")
    @Expose
    var precipitation: Precipitation? = null
    @SerializedName("windDirection")
    @Expose
    var windDirection: WindDirection? = null
    @SerializedName("windSpeed")
    @Expose
    var windSpeed: WindSpeed? = null
    @SerializedName("temperature")
    @Expose
    var temperature: Temperature? = null
    @SerializedName("pressure")
    @Expose
    var pressure: Pressure? = null
    @SerializedName("humidity")
    @Expose
    var humidity: Humidity? = null
    @SerializedName("clouds")
    @Expose
    var clouds: Clouds? = null

}

class Humidity {

    @SerializedName("@value")
    @Expose
    var value: String? = null
    @SerializedName("@unit")
    @Expose
    var unit: String? = null

}

class Location {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("type")
    @Expose
    var type: List<Any>? = null
    @SerializedName("country")
    @Expose
    var country: String? = null
    @SerializedName("timezone")
    @Expose
    var timezone: List<Any>? = null
    @SerializedName("location")
    @Expose
    var location: Location  ? = null

}

class Meta {

    @SerializedName("lastupdate")
    @Expose
    var lastupdate: List<Any>? = null
    @SerializedName("calctime")
    @Expose
    var calctime: String? = null
    @SerializedName("nextupdate")
    @Expose
    var nextupdate: List<Any>? = null

}

class Precipitation {

    @SerializedName("@unit")
    @Expose
    var unit: String? = null
    @SerializedName("@value")
    @Expose
    var value: String? = null

}

class Pressure {

    @SerializedName("@unit")
    @Expose
    var unit: String? = null
    @SerializedName("@value")
    @Expose
    var value: String? = null

}

class Sun {

    @SerializedName("@rise")
    @Expose
    var rise: String? = null
    @SerializedName("@set")
    @Expose
    var set: String? = null

}

class Symbol {

    @SerializedName("@number")
    @Expose
    var number: String? = null
    @SerializedName("@name")
    @Expose
    var name: String? = null
    @SerializedName("@var")
    @Expose
    var `var`: String? = null

}

class Temperature {

    @SerializedName("@unit")
    @Expose
    var unit: String? = null
    @SerializedName("@value")
    @Expose
    var value: String? = null
    @SerializedName("@min")
    @Expose
    var min: String? = null
    @SerializedName("@max")
    @Expose
    var max: String? = null

}

class WindDirection {

    @SerializedName("@deg")
    @Expose
    var deg: String? = null
    @SerializedName("@code")
    @Expose
    var code: String? = null
    @SerializedName("@name")
    @Expose
    var name: String? = null

}

class WindSpeed {

    @SerializedName("@mps")
    @Expose
    var mps: String? = null
    @SerializedName("@name")
    @Expose
    var name: String? = null

}
