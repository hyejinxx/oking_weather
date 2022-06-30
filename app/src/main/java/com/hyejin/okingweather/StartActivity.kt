package com.hyejin.okingweather

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.gms.location.*
import com.hyejin.okingweather.data.ModelWeather
import com.hyejin.okingweather.data.Weather
import com.hyejin.okingweather.data.WeatherList
import kotlinx.coroutines.flow.collect
import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import kotlin.concurrent.thread

class StartActivity : AppCompatActivity() {

    private val viewModel by viewModels<StartViewModel>()

    private var REQUEST_CODE = 1313

    //  권한 요청
    private fun requestPermissions(): Boolean{
        val locationPermission: Int = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
        if( locationPermission == PackageManager.PERMISSION_GRANTED){
            return true
        } else (return false)
    }
    // 권한 요청 완료시 이 함수를 호출해 권한 요청에 대한 결과를 argument로 받을 수 있음
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty()){
                    for(grant in grantResults){
                        var isAllGranted = true
                        if(grant != PackageManager.PERMISSION_GRANTED) {
                            isAllGranted = false
                            break
                        }
                        if (isAllGranted){
                            Toast.makeText(this, "위치 권한 허용 완료", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this, "위치 권한을 승인하지 않으면 현재 위치 기반으로 날씨 정보를 알려드릴 수 없습니다.", Toast.LENGTH_SHORT).show()
                            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)){
                                requestPermissions()
                            }else{
                                break
                            }
                        }
                    }
                }
            }
        }
    }
    private fun checkLocationPermission(){
        if (requestPermissions()){
//            checkLocationSettng()
        }else{
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)
        }
    }
    private var mLastLocation : Location? =null
    private var mFusedLocationProviderClient : FusedLocationProviderClient? = null
    private lateinit var mLocationRequest: LocationRequest

    private var address : List<Address>? = null
    private var addrAdmin : String? =null
    private var addrLocality : String? = null

    private lateinit var progressBar : ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        initView()

        mLocationRequest = LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        startLocationUpdate()

        mLastLocation?.let {
            viewModel.requestData(mLastLocation!!.latitude, mLastLocation!!.longitude)
            getAddr(mLastLocation!!.longitude, mLastLocation!!.longitude)
        }

        lifecycleScope.launchWhenCreated {
            viewModel.weatherData.collect {
                if (it != null) {
                    data(it)
                }else{
                   // Toast.makeText(this@StartActivity, "날씨 정보 조회에 실패했습니다\n조회 가능한 지역이 아닙니다", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun initView(){
        val mainImageView : ImageView = findViewById(R.id.main_image)
        progressBar  = findViewById(R.id.progressBar)
        Glide.with(this).load(R.raw.gif1).into(mainImageView)

        var progress = 0


        Thread{
            try {
                while (progress <=100){
                    Thread.sleep(25)
                    progressBar.incrementProgressBy(1)
                    progress++
                }
            }catch (e : Exception){
                /*no-op*/
            }
        }.start()
    }
    private fun getAddr(lat: Double, lon: Double){
        val g = Geocoder(this)
        try {
            address = g.getFromLocation(lat, lon, 10)
        }catch (e : IOException){
            Toast.makeText(this, "입출력 오류", Toast.LENGTH_SHORT).show()
        }
        if(address!=null){
             if(address!!.isEmpty()){
                addrAdmin = "위치 정보"
                 addrLocality = "없음"
            }else{
                if(address!![0].adminArea!=null) {
                    addrAdmin = address!![0].adminArea
                }

                for (i in 0..10){
                    if(address!![i].locality!=null){
                        addrLocality = address!![i].locality
                        if(addrAdmin == addrLocality){
                            addrLocality = " "
                        }
                        break
                    }

                }
            }
        }
    }
    private fun data(weatherData : ArrayList<Weather.Item>){
        val weatherList = WeatherList(ModelWeather(), ModelWeather(), ModelWeather(), ModelWeather(), ModelWeather(), ModelWeather())

        for(j in 0..5) {
            val _weather = ModelWeather()
            for (i in j until weatherData.size step 6) {
                when (weatherData[i].category) {
                    "PTY" -> _weather.rainType = weatherData[i].fcstValue // 강수형태
                    "REH" -> _weather.humidity = weatherData[i].fcstValue // 습도
                    "SKY" -> _weather.sky = weatherData[i].fcstValue // 하늘상태
                    "T1H" -> _weather.temp = weatherData[i].fcstValue // 기온
                    "WSD" -> _weather.wind = weatherData[i].fcstValue
                }
            }
            _weather.rainType = getRainType(_weather.rainType)
            _weather.sky = getSky(_weather.sky)
            _weather.wind = getWind(_weather.wind.toInt())
            _weather.addrLocality = addrLocality.toString()
            _weather.addrAdmin = addrAdmin.toString()

            when(j){
                0 -> weatherList.weatherNow = _weather
                1 -> weatherList.weatherAfter1Hour = _weather
                2 -> weatherList.weatherAfter2Hour = _weather
                3 -> weatherList.weatherAfter3Hour = _weather
                4 -> weatherList.weatherAfter4Hour= _weather
                5 -> weatherList.weatherAfter5Hour = _weather
            }
        }

        toShowActivity(weatherList)
    }
    private fun toShowActivity(weatherList: WeatherList) {
        val intent = Intent(this, ShowActivity::class.java)
        intent.putExtra("weather", weatherList)
        startActivity(intent)
    }
    private fun startLocationUpdate(){
        checkLocationPermission()

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationProviderClient!!.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper()!!)
    }

    private var mLocationCallback = object  : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            locationResult.lastLocation
            mLastLocation = locationResult.lastLocation
            changeLocation(locationResult.lastLocation)
        }
    }
    fun changeLocation(location: Location){
        mLastLocation = location
    }
    fun getRainType(rainType: String): String {
        return when (rainType) {
            "0" -> "없음"
            "1" -> "비"
            "2" -> "비/눈"
            "3" -> "눈"
            "5" -> "빗방울"
            "6" -> "빗방울 눈날림"
            "7" -> "눈날림"
            else -> "오류 rainType" + rainType
        }
    }

    fun getSky(sky: String): String {
        return when (sky) {
            "1" -> "맑음"
            "3" -> "구름 많음"
            "4" -> "흐림"
            else -> "오류 sky : " + sky
        }
    }

    fun getWind(wind : Int): String{
        return when{
            wind < 4 -> "바람 약함"
            wind < 9 -> "바람 약간 강하게 붐 "
            wind < 14 -> "바람 강하게 붐"
            else -> "바람 매우 강하게 붐"
        }
    }
}

