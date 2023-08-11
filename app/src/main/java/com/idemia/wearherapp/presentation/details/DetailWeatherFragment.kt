package com.idemia.wearherapp.presentation.details

import android.graphics.drawable.GradientDrawable.Orientation
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.idemia.wearherapp.R
import com.idemia.wearherapp.data.models.DataWeather
import com.idemia.wearherapp.databinding.FragmentDetailWeatherBinding
import com.idemia.wearherapp.presentation.details.adapter.MonthWeatherAdapter
import com.idemia.wearherapp.presentation.weatherhome.adapter.WeatherDataApdpter


class DetailWeatherFragment : Fragment() {

    lateinit var binding: FragmentDetailWeatherBinding

    lateinit var detaildata : DataWeather

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        detaildata =   getArguments()?.getSerializable("weatherdetail") as DataWeather
        binding = FragmentDetailWeatherBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initComponent()
    }

    fun initComponent(){
        binding.currentposition.content.visibility =View.VISIBLE
        binding.currentposition.loading.visibility = View.GONE
        detaildata.let {

            binding.currentposition.position.text = it.request[0].query
            binding.currentposition.degree.text = it.currentCondition[0].temp_C
            Glide.with(requireContext()).load(it.currentCondition[0].weatherIconUrl[0].value).placeholder(R.drawable.ic_baseline_data_holding_24).dontAnimate().into(binding.currentposition.cloud);

            binding.currentposition.cloudtext.text = it.currentCondition[0].weatherDesc[0].value
            binding.sunset.img.setImageDrawable(requireContext().getDrawable(R.drawable.ic_baseline_brightness_4_24))
            binding.sunset.value.text = it.weatherdetail[0].astronomy[0].sunset

            binding.sunup.img.setImageDrawable(requireContext().getDrawable(R.drawable.ic_baseline_sun_1_24))
            binding.sunup.value.text = it.weatherdetail[0].astronomy[0].sunrise

            binding.moon.img.setImageDrawable(requireContext().getDrawable(R.drawable.ic_baseline_moon_1_24))
            binding.moon.value.text = it.weatherdetail[0].astronomy[0].moonrise

            var adapter = MonthWeatherAdapter(it.climateAverages.get(0).month)
            val gridLayoutManager = GridLayoutManager(requireContext(), 2)  // 3 columns

            binding.list.layoutManager =gridLayoutManager
            binding.list.adapter = adapter

        }
    }


}