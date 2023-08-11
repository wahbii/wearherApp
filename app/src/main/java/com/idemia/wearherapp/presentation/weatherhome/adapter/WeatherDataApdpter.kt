package com.idemia.wearherapp.presentation.weatherhome.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.idemia.wearherapp.R

import com.idemia.wearherapp.data.models.WeatherResponse
import com.idemia.wearherapp.databinding.ItemWeatherListBinding
import com.idemia.wearherapp.utils.di.interfaces.OnclickItem

class WeatherDataApdpter(var datalist :List<WeatherResponse>,var clickListener: OnclickItem) : Adapter<WeatherDataApdpter.WeatherViewHolder>() {


    fun setData(list : List<WeatherResponse>){
        datalist = list
        notifyDataSetChanged()
    }


    class WeatherViewHolder(var v:ItemWeatherListBinding) : ViewHolder(v.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        var v = ItemWeatherListBinding.inflate(LayoutInflater.from(parent.context))
        return WeatherViewHolder(v)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            clickListener.onclickItem(position)
        }

        with(datalist.get(position)) {

            data.request.let {
                holder.v.city.text =  it?.get(0).query
            }
            data.currentCondition?.get(0).weatherIconUrl.get(0).value.let {

                Glide.with(holder.itemView.context).load(it).placeholder(R.drawable.ic_baseline_data_holding_24).dontAnimate().into(holder.v.img);

            }
            data.currentCondition?.get(0).weatherDesc.get(0).value.let {
                holder.v.title
            }
            data.currentCondition.get(0).let {
                holder.v.humidity.value.text = it.humidity.toString()
                holder.v.humidity.img.setImageDrawable(holder.itemView.context.getDrawable(
                    R.drawable.ic_baseline_water_drop_24))
                holder.v.pressure.value.text = it.temp_C.toString()+"° C"
                holder.v.pressure.img.setImageDrawable(holder.itemView.context.getDrawable(
                    R.drawable.ic_baseline_whatshot_24))
                holder.v.speed.value.text = it.windspeedKmph.toString() + " KM"
                holder.v.speed.img.setImageDrawable(holder.itemView.context.getDrawable(
                    R.drawable.ic_baseline_air_24))

            }
        }

    }

    override fun getItemCount(): Int = datalist.size


}