package com.hyejin.okingweather.finedust

import com.hyejin.okingweather.data.Weather
import com.hyejin.okingweather.weather.WeatherInterface
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

data class FineDust(
    val response : Response
) {
    data class Response(
        val header: Header,
        val body: Body
    )
    data class Header(
        val resultCode: Int,
        val resultMsg: String
    )
    data class Body(
        val items: Items,
        val totalCount: Int
    )
    data class Items(
        val item: ArrayList<Item>
    )

    data class Item(
        val category: String,
        val baseDate: String,
        val baseTime: String,
        val fcstValue: String
    )
}


val retrofit = Retrofit.Builder()
    .baseUrl("http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object ApiObject {
    val retrofitService: FineDustInterface by lazy {
        retrofit.create(FineDustInterface::class.java)
    }
}