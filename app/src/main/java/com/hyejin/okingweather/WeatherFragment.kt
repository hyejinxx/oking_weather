package com.hyejin.okingweather

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import androidx.annotation.Dimension
import com.hyejin.okingweather.data.WeatherList
import com.hyejin.okingweather.databinding.FragmentWeatherBinding
import java.text.SimpleDateFormat
import java.util.*

class WeatherFragment : Fragment() {

    private lateinit var binding: FragmentWeatherBinding
    private var weather: WeatherList? = null

    private val date =
        System.currentTimeMillis().let { current ->
            SimpleDateFormat("yyyy년 MM월 dd일 HH시").format(current)
        }
    private val time = System.currentTimeMillis().let { current ->
        SimpleDateFormat("HH").format(current)
    }.toInt()
    private var timeArray = arrayOf(time + 1, time + 2, time + 3, time + 4, time + 5)

    private val okingImageGood =
        arrayOf(R.drawable.oking_good, R.drawable.oking_good2, R.drawable.oking_good3, R.drawable.oking_good4)
    private val okingImageHot =
        arrayOf(R.drawable.oking_hot, R.drawable.oking_hot2, R.drawable.oking_hot3)
    private val okingImageRain = arrayOf(R.drawable.oking_rain, R.drawable.oking_rain2, R.drawable.oking_rain3, R.drawable.oking_rain4)
    private val okingImageCloud =
        arrayOf(R.drawable.oking_cloud, R.drawable.oking_cloud3)
    private val okingImageMorning = arrayOf(R.drawable.mornig2, R.drawable.mornig2)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)

        arguments?.let {
            weather = it.getSerializable("weather") as WeatherList
        }
        initView()

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        timeArray = timeArrange(timeArray)

        binding.viewBack.setBackgroundResource( // 배경색
            backColor(11)
        )

        // 날씨, 날짜, 주소 텍스트
        binding.addrTextView.text =
            weather!!.weatherAfter1Hour.addrAdmin + weather!!.weatherAfter2Hour.addrLocality
        binding.dateTextView.text = date
        binding.tempTextView.text = " " + weather!!.weatherNow.temp + "°"
        binding.windText.text = weather!!.weatherNow.wind + "  "

        // 날씨 아이콘
        binding.skyImage.setImageResource(
            skyImage(weather!!.weatherNow.sky, weather!!.weatherNow.rainType)
        )
        binding.windImage2.setImageResource(
            when (weather!!.weatherNow.sky) {
                "바람 약함" -> R.drawable.windyx
                "바람 약간 강하게 붐 " -> R.drawable.windy1
                "바람 강하게 붐" -> R.drawable.windy2
                "바람 매우 강하게 붐" -> R.drawable.windy3
                else -> R.drawable.windyx
            }
        )
        // 습도
        binding.humidityText.text = weather!!.weatherNow.humidity + "%"

        // 시간별 날씨 텍스트 아이콘
        binding.timeTextAfter1Hour.text = timeArray[0].toString() + "시"
        binding.timeTextAfter2Hour.text = timeArray[1].toString() + "시"
        binding.timeTextAfter3Hour.text = timeArray[2].toString() + "시"
        binding.timeTextAfter4Hour.text = timeArray[3].toString() + "시"
        binding.timeTextAfter5Hour.text = timeArray[4].toString() + "시"

        binding.tempTextAfter1Hour.text = weather!!.weatherAfter1Hour.temp + "°"
        binding.tempTextAfter2Hour.text = weather!!.weatherAfter2Hour.temp + "°"
        binding.tempTextAfter3Hour.text = weather!!.weatherAfter3Hour.temp + "°"
        binding.tempTextAfter4Hour.text = weather!!.weatherAfter4Hour.temp + "°"
        binding.tempTextAfter5Hour.text = weather!!.weatherAfter5Hour.temp + "°"

        binding.skyImageAfter1Hour.setImageResource(
            skyImage(weather!!.weatherAfter1Hour.sky, weather!!.weatherAfter1Hour.rainType)
        )
        binding.skyImageAfter2Hour.setImageResource(
            skyImage(weather!!.weatherAfter2Hour.sky, weather!!.weatherAfter2Hour.rainType)
        )
        binding.skyImageAfter3Hour.setImageResource(
            skyImage(weather!!.weatherAfter3Hour.sky, weather!!.weatherAfter3Hour.rainType)
        )
        binding.skyImageAfter4Hour.setImageResource(
            skyImage(weather!!.weatherAfter4Hour.sky, weather!!.weatherAfter4Hour.rainType)
        )
        binding.skyImageAfter5Hour.setImageResource(
            skyImage(weather!!.weatherAfter5Hour.sky, weather!!.weatherAfter5Hour.rainType)
        )

        //날씨, 시간별 오킹 사진
        binding.okingImage.setImageResource(
            okingImage(11, "흐림", weather!!.weatherNow.rainType, weather!!.weatherAfter1Hour.rainType, weather!!.weatherNow.temp, weather!!.weatherNow.wind)
        )
    }

    private fun skyImage(sky: String, raintype: String): Int {
        val image = if (raintype == "없음") {
            when (sky) {
                "맑음" -> R.drawable.sun
                "구름 많음" -> R.drawable.cloud_sun
                "흐림" -> R.drawable.cloud
                else -> R.drawable.sun
            }
        } else {
            when (raintype) {
                "비" -> R.drawable.rain
                "비/눈" -> R.drawable.snow_rain
                "눈" -> R.drawable.snow_cloud
                "빗방울" -> R.drawable.rain_
                "빗방울 눈날림" -> R.drawable.windysnowrain
                "눈날림" -> R.drawable.snow_cloud
                else -> R.drawable.rain_
            }
        }
        return image
    }

    private fun okingImage(
        time: Int,
        sky: String,
        raintype: String,
        raintypeAfter1Hour: String,
        temp: String,
        wind: String
    ): Int {
        // 오전에는 morning뱅미, 비올때는 우산뱅미, 30도 넘을때는 hot뱅미,
        val random = Random()
        var okingImage: Int
        val ranHotRainGood = random.nextInt(4)
        val ranMorningCloud = random.nextInt(2)

        if (temp.toInt() >= 30) { // 더울때
            if (time in 7..9) {
                okingImage = R.drawable.morning // 오전일때
                binding.okingText.text= "이렇게 더운데\n나갈거야..?"
            } else {
                okingImage = okingImageHot[ranHotRainGood]
                if(okingImage == R.drawable.oking_hot){
                    binding.okingText.text = "\n\n\n나가지 마세요"
                }else{
                    binding.okingText.setTextSize(Dimension.SP, 17F)
                    binding.okingText.text = "개더워..."
                }
            }
        } else if (temp.toInt() <= 5) { // 추울때
            okingImage = R.drawable.oking_snow
        } else {
            if (time in 7..9) {
                if(raintype == "비" || raintype == "비/눈" || raintype == "빗방울 눈날림" || raintype == "빗방울"){
                    okingImage = R.drawable.oking_rain4
                    binding.okingText.text= "비도오는데 \n무슨 출근이야 \n재껴"
                }else {
                    okingImage = okingImageMorning[ranMorningCloud] // 오전일때
                    if(okingImage==R.drawable.morning){
                        binding.okingText.text = "출근해..?  잘다녀와.."
                    }else {
                        binding.okingText.text = "날씨 좋은데 \n무슨 출근이야 \n재껴"
                    }
                }
            } else {
                if (sky == "맑음") { // 맑을때
                    if (wind == "바람 매우 강하게 붐") { // 맑고 바람 많이불때
                        okingImage = R.drawable.oking_windy2
                        binding.okingText.text = "밖에 나가면\n날라갈지도 몰라"
                    } else {
                        okingImage = okingImageGood[ranHotRainGood] // 맑을때
                        if(okingImage == R.drawable.oking_good3|| okingImage == R.drawable.oking_good4){
                            binding.okingText.text = "\n날씨도 좋은데\n축구ㄱ?"
                        }else{
                            binding.okingText.text = ""
                        }
                    }
                } else { // 맑음 아닐때
                    if ( raintype == "비" || raintype == "비/눈" || raintype == "빗방울 눈날림" || raintypeAfter1Hour == "빗방울" || raintypeAfter1Hour == "비" || raintypeAfter1Hour == "비/눈" || raintypeAfter1Hour == "빗방울 눈날림" || raintypeAfter1Hour == "빗방울") { //비올때
                        okingImage = okingImageRain[ranHotRainGood]
                        when(okingImage){
                            R.drawable.oking_rain -> binding.okingText.text = "밖에\n비온다고??"
                            R.drawable.oking_rain2 -> binding.okingText.text = "\n우산 챙겨나강"
                            R.drawable.oking_rain3 -> binding.okingText.text ="축구할라구\n했는뎅"
                            R.drawable.oking_rain4 -> binding.okingText.text ="\n야 나가지마\n비온대"}
                    } else if (raintype == "눈" || raintype == "눈날림") { // 눈올때
                        okingImage = R.drawable.oking_snow
                        binding.okingText.text = "밖에 눈온대\n(설렘)"
                    } else {
                        okingImage = okingImageCloud[ranMorningCloud] // 흐릴때
                        when(okingImage){
                            R.drawable.oking_cloud -> binding.okingText.text ="\n날씨 개구려  "
                            R.drawable.oking_cloud3 -> binding.okingText.text = "날씨 흐려도          \n축구 할거얍"
                        }

                    }
                }
            }
        }
        val ran = random.nextInt(10)
        if (ran == 3){
            okingImage = R.drawable.oking
        }
        return okingImage
    }
    private fun backColor(time : Int): Int{
        val color = if((time in 0..6)||(21 <= time)){
            R.drawable.night_gradient
        }else if(time in 7..17){
            R.drawable.day_gradient
        }else{
            R.drawable.evening_gradient
        }
        return color
    }
    private fun timeArrange(timeArray : Array<Int>) : Array<Int>{
        for(i in 0..4){
            if(timeArray[i]>=24){
                timeArray[i] -= 24
            }
        }
        return timeArray
    }
}