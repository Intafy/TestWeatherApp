package com.example.weathertestapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.weathertestapp.MainViewModel
import com.example.weathertestapp.R
import com.example.weathertestapp.databinding.FragmentMainBinding
import com.example.weathertestapp.databinding.FragmentTodayBinding

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
        model.liveDateCurrent.observe(viewLifecycleOwner){
//        val wind=it.windDirection
        tvTempMax.text= it.maxTemp+"°C"
        tvTempMin.text=it.minTemp+"°C"
//        tvHumidity.text=it.humidity
//        tvWindValue.text=wind
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
