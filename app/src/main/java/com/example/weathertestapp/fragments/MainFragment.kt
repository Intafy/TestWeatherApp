package com.example.weathertestapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weathertestapp.DayItem
import com.example.weathertestapp.FragmentAdapter
import com.example.weathertestapp.MainViewModel
import com.example.weathertestapp.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import org.json.JSONObject

const val API_KEY = "78c6ae22ba284012a69200548242609"

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val fList = listOf(
        TodayFragment.newInstance(),
        TomorrowFragment.newInstance(),
        AfterTomorrowFragment.newInstance()
    )
    private val tList = listOf(
        "Сегодня",
        "Завтра",
        "Послезавтра"
    )
    private val model:MainViewModel by activityViewModels()
    private val city = "Saratov"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        onUpdateCurrentCard()
        onRequestWeatherData(city)

    }
    private fun onRequestWeatherData(city:String){
        val url = "https://api.weatherapi.com/v1/forecast.json?key="+
                API_KEY+
                "&q="+
                city+
                "&days="+
                "3"+
                "&aqi=no&alerts=no"
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,url,{
                result->onParseWeatherData(result)
            },{
                error->
                Log.d("MyLog","Error: $error")
            }
        )
        queue.add(request)
    }

    private fun onParseWeatherData(result: String) {
        val mainObject = JSONObject(result)
        val list = onParseDays(mainObject)
        onParseCurrentData(mainObject,list[0])
    }

    private fun onParseCurrentData(mainObject: JSONObject,weatherItem: DayItem){
        val item = DayItem(
            mainObject.getJSONObject("location").getString("name"),
            mainObject.getJSONObject("current").getString("last_updated"),
            mainObject.getJSONObject("current").getJSONObject("condition").getString("text"),
            mainObject.getJSONObject("current").getString("temp_c")+"\u00B0"+"C",
            mainObject.getJSONObject("current").getJSONObject("condition").getString("icon"),
            weatherItem.maxTemp,
            weatherItem.minTemp,
            "",
            "",
            ""
        )
        model.liveDateCurrent.value = item

        Log.d("MyLog","City: ${item.city}")
        Log.d("MyLog","Time: ${item.time}")
        Log.d("MyLog","Condition: ${item.condition}")
        Log.d("MyLog","Temp: ${item.currentTemp}")
        Log.d("MyLog","Url: ${item.imageUrl}")
        Log.d("MyLog","MaxTemp: ${item.maxTemp}")
        Log.d("MyLog","MinTemp: ${item.minTemp}")
//        Log.d("MyLog","Humidity: ${item.humidity}")
//        Log.d("MyLog","Wind dir: ${item.windDirection}")
//        Log.d("MyLog","Wind speed: ${item.windSpeed}")
    }

    private fun onParseDays(mainObject: JSONObject):List<DayItem>{
        val list = ArrayList<DayItem>()
        val name = mainObject.getJSONObject("location").getString("name")
        val daysArray = mainObject.getJSONObject("forecast").getJSONArray("forecastday")
        for(i in 0 until daysArray.length()){
            val day = daysArray[i] as JSONObject
            val item = DayItem(
                name,
                day.getString("date"),
                day.getJSONObject("day").getJSONObject("condition").getString("text"),
                "",
                day.getJSONObject("day").getJSONObject("condition").getString("icon"),
                day.getJSONObject("day").getString("maxtemp_c"),
                day.getJSONObject("day").getString("mintemp_c"),
                day.getJSONObject("day").getString("avghumidity"),
                "WWWWW",
                day.getJSONObject("day").getString("maxwind_kph")
            )
            list.add(item)
            model.liveDateList.value=list
        }
        return list
    }

    private fun init() = with(binding){
        val adapter = FragmentAdapter(activity as FragmentActivity,fList)
        vp.adapter=adapter
        TabLayoutMediator(tabLayout,vp){
            tab, pos -> tab.text=tList[pos]
        }.attach()
    }

    private fun onUpdateCurrentCard()=with(binding){
        model.liveDateCurrent.observe(viewLifecycleOwner){
        val maxMinTemp = "${it.maxTemp}"+"\u00B0"+"C"+"/"+"${it.minTemp}"+"\u00B0"+"C"
        tvCity.text=it.city
        tvDate.text=it.time
        tvCurrentTemp.text=it.currentTemp
        tvCondition.text=it.condition
        tvMaxMin.text=maxMinTemp
        Picasso.get().load("https:" +it.imageUrl).into(imWeather)

        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}