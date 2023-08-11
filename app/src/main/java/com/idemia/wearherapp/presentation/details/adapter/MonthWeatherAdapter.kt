package com.idemia.wearherapp.presentation.details.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.idemia.wearherapp.data.models.Months
import com.idemia.wearherapp.databinding.ItemWeatherListBinding
import com.idemia.wearherapp.databinding.LayoutMonthWeatherBinding
import com.idemia.wearherapp.presentation.weatherhome.adapter.WeatherDataApdpter

class MonthWeatherAdapter(var data : List<Months>) :
    Adapter<MonthWeatherAdapter.MonthWeatherHolder>() {

    class MonthWeatherHolder(var v: LayoutMonthWeatherBinding) : RecyclerView.ViewHolder(v.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthWeatherHolder {
        var v = LayoutMonthWeatherBinding.inflate(LayoutInflater.from(parent.context))
        return MonthWeatherAdapter.MonthWeatherHolder(v)
    }

    override fun onBindViewHolder(holder: MonthWeatherHolder, position: Int) {
        with(data.get(position)) {
            holder.v.month.text = name
            holder.v.absMaxTemp.text = absMaxTemp.toString()
            holder.v.avgDailyRainfall.text =  avgDailyRainfall.toString()
            holder.v.avgMinTemp.text  = avgMinTemp.toString()
        }
    }

    override fun getItemCount(): Int = data.size
}