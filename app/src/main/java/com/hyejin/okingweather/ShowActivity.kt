package com.hyejin.okingweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.hyejin.okingweather.data.WeatherList
import java.io.Serializable

class ShowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        val intent = intent
        val weather = intent.getSerializableExtra("weather") as WeatherList
        setFrag(weather, WeatherFragment())

    }
    private fun setFrag(weather: WeatherList, fragment: Fragment) {
        val bundle = Bundle()
        bundle.putSerializable("weather", weather)
        fragment.arguments = bundle

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainframe, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
