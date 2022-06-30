package com.hyejin.okingweather

import android.location.Geocoder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyejin.okingweather.data.*
import com.hyejin.okingweather.data.Weather.Item
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.withIndex
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.lang.NullPointerException
import java.text.SimpleDateFormat

class StartViewModel() : ViewModel() {

    var responseCheck = MutableStateFlow(false)
    var weatherData = MutableStateFlow<ArrayList<Weather.Item>?>(null)


    private val _date = MutableStateFlow(
        System.currentTimeMillis().let { current ->
            Date(
                SimpleDateFormat("yyyyMMdd").format(current),
                SimpleDateFormat("HH").format(current),
                SimpleDateFormat("mm").format(current)
            )
        }
    )
    private val date = _date.asStateFlow()

    private var nx = MutableStateFlow("61")
    private var ny = MutableStateFlow("125")

    fun requestData(lat: Double, lon: Double) {
        val location = dfs_xy_conv(lat, lon)
        nx.value = location.x.toString()
        ny.value = location.y.toString()

        viewModelScope.launch {

            delay(2500)

            val time = getTime(date.value.timeH, date.value.timeM)

            val call = ApiObject.retrofitService.getWeather(
                num_of_rows = 60,
                page_no = 2,
                data_type = "JSON",
                base_date = date.value.date,
                base_time = time,
                nx = nx.value,
                ny = ny.value
            )

            call.enqueue(object : retrofit2.Callback<Weather> {
                override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                    if (response.isSuccessful) {
                        try {
                            weatherData.value = response.body()!!.response.body.items.item
                            responseCheck.value = true
                        } catch (e: NullPointerException) {
                            responseCheck.value = false
                        }
                    }

                }

                override fun onFailure(call: Call<Weather>, t: Throwable) {
                    /**/
                }
            })
        }
    }

    private fun getTime(h: String, m: String): String {
        var result = ""

        result = if (m.toInt() < 45) {
            // 0시면 2330
            if (h == "00") "2330"
            // 아니면 1시간 전 날씨 정보 부르기
            else {
                val resultH = h.toInt() - 1
                // 1자리면 0 붙여서 2자리로 만들기
                if (resultH < 10) "0" + resultH + "30"
                // 2자리면 그대로
                else resultH.toString() + "30"
            }
        }
        // 45분 이후면 바로 정보 받아오기
        else h + "30"

        if(result=="2330"){
            date.value.date = (date.value.date.toInt()-1).toString()
        }

        return result
    }

    fun dfs_xy_conv(v1: Double, v2: Double): LocationXY {
        val RE = 6371.00877     // 지구 반경(km)
        val GRID = 5.0          // 격자 간격(km)
        val SLAT1 = 30.0        // 투영 위도1(degree)
        val SLAT2 = 60.0        // 투영 위도2(degree)
        val OLON = 126.0        // 기준점 경도(degree)
        val OLAT = 38.0         // 기준점 위도(degree)
        val XO = 43             // 기준점 X좌표(GRID)
        val YO = 136            // 기준점 Y좌표(GRID)
        val DEGRAD = Math.PI / 180.0
        val re = RE / GRID
        val slat1 = SLAT1 * DEGRAD
        val slat2 = SLAT2 * DEGRAD
        val olon = OLON * DEGRAD
        val olat = OLAT * DEGRAD

        var sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5)
        sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn)
        var sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5)
        sf = Math.pow(sf, sn) * Math.cos(slat1) / sn
        var ro = Math.tan(Math.PI * 0.25 + olat * 0.5)
        ro = re * sf / Math.pow(ro, sn)

        var ra = Math.tan(Math.PI * 0.25 + (v1) * DEGRAD * 0.5)
        ra = re * sf / Math.pow(ra, sn)
        var theta = v2 * DEGRAD - olon
        if (theta > Math.PI) theta -= 2.0 * Math.PI
        if (theta < -Math.PI) theta += 2.0 * Math.PI
        theta *= sn

        val x = (ra * Math.sin(theta) + XO + 0.5).toInt()
        val y = (ro - ra * Math.cos(theta) + YO + 0.5).toInt()

        return LocationXY(x, y)
    }
}
