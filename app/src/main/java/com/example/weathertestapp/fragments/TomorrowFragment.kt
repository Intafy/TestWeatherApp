package com.example.weathertestapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.weathertestapp.MainViewModel
import com.example.weathertestapp.R
import com.example.weathertestapp.databinding.FragmentTodayBinding
import com.example.weathertestapp.databinding.FragmentTomorrowBinding
import com.squareup.picasso.Picasso

class TomorrowFragment : Fragment() {
private lateinit var binding: FragmentTodayBinding
private val model:MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentTodayBinding.inflate(inflater,container,false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onUpdateInfo()

    }
    private fun onUpdateInfo()=with(binding){
        model.liveDateList.observe(viewLifecycleOwner){

            tvForecastDate.text=it[1].time
            tvForecastCondition.text=it[1].condition
            Picasso.get().load("https:" +it[1].imageUrl).into(imForecastCondition)
            tvTempMax.text= it[1].maxTemp+"°C"
            tvTempMin.text=it[1].minTemp+"°C"
            tvHumiditeValue.text=it[1].humidity + " %"
            tvWindValue.text=it[1].windSpeed + " km/h"

        }
    }
    companion object {
        @JvmStatic
        fun newInstance()=TomorrowFragment()
    }

}