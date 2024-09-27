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


class AfterTomorrowFragment : Fragment() {
    private lateinit var binding: FragmentTodayBinding
    private val model: MainViewModel by activityViewModels()
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

            tvForecastDate.text=it[2].time
            tvForecastCondition.text=it[2].condition
            Picasso.get().load("https:" +it[2].imageUrl).into(imForecastCondition)
            tvTempMax.text= it[2].maxTemp+"°C"
            tvTempMin.text=it[2].minTemp+"°C"
            tvHumiditeValue.text=it[2].humidity + " %"
            tvWindValue.text=it[2].windSpeed + " km/h"
        }
    }

    companion object {
        @JvmStatic
        fun newInstance()=AfterTomorrowFragment()
    }

}