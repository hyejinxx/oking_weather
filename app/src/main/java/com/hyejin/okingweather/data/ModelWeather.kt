package com.hyejin.okingweather.data

import java.io.Serializable

data class ModelWeather(
                        var rainType : String = "",
                        var humidity : String = "",
                        var sky : String = "",
                        var temp : String = "",
                        var wind : String = "",
                        var addrAdmin : String = "",
                        var addrLocality : String = ""
) : Serializable
data class WeatherList(
    var weatherNow: ModelWeather,
    var weatherAfter1Hour: ModelWeather,
    var weatherAfter2Hour: ModelWeather,
    var weatherAfter3Hour: ModelWeather,
    var weatherAfter4Hour: ModelWeather,
    var weatherAfter5Hour: ModelWeather):Serializable
