package com.example.weathertestapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.weathertestapp.MainViewModel
import com.example.weathertestapp.databinding.FragmentTodayBinding
import com.squareup.picasso.Picasso

class TodayFragment : Fragment() {
    private lateinit var binding: FragmentTodayBinding
    private val model:MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentTodayBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun onUpdateInfo()=with(binding){
        model.liveDateList.observe(viewLifecycleOwner){

        tvForecastDate.text=it[0].time
        tvForecastCondition.text=it[0].condition
        Picasso.get().load("https:" +it[0].imageUrl).into(imForecastCondition)
        tvTempMax.text= it[0].maxTemp+"°C"
        tvTempMin.text=it[0].minTemp+"°C"
        tvHumiditeValue.text=it[0].humidity + " %"
        tvWindValue.text=it[0].windSpeed + " km/h"
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onUpdateInfo()

    }

    companion object {
        @JvmStatic
        fun newInstance()=TodayFragment()
    }
}
