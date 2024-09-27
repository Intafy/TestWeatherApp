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

            tvTempMax.text= it[1].maxTemp
            tvTempMin.text=it[1].minTemp

        }
    }
    companion object {
        @JvmStatic
        fun newInstance()=TomorrowFragment()
    }

}