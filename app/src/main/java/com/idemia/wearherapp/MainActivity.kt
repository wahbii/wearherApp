package com.idemia.wearherapp

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.idemia.wearherapp.data.source.local.SharedPreferencesStorage
import com.idemia.wearherapp.data.source.network.api.ApiService
import com.idemia.wearherapp.databinding.ActivityMainBinding
import com.idemia.wearherapp.domain.usecase.cases.GetWeatherByLocationUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding


    companion object{

        var cities = mutableListOf<String>("Casablanca Morocco","Rabat Morocco","Marrakech Morocco","Tangier Morocco" ,"Fes Morocco")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }




    fun removecity(city :String) {
           cities.remove(city)
    }

    // adding city
    fun addCity(city:String) {
        cities.add(city+ " Morocco")

    }

    // popup if the device not connected to internet
    fun showDialog(massage :String){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_linternet_not_found)
        var btn = dialog.findViewById<TextView>(R.id.open)
        btn.setOnClickListener {
            dialog.dismiss()
            finishAffinity()
        }
        var text = dialog.findViewById<TextView>(R.id.ip_edit_text)
        text.text = massage
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val widthLcl = (displayMetrics.widthPixels * 0.9f).toInt()
        val heightLcl = (displayMetrics.heightPixels * 0.2f).toInt()
        val paramsLcl = dialog.window?.attributes
        paramsLcl?.width = widthLcl
        paramsLcl?.height = heightLcl
        paramsLcl?.gravity = Gravity.CENTER
        dialog.window?.attributes = paramsLcl
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.show()

    }

    // popup to add city
    fun showDialogAddCity(onClick:()-> Unit){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.layout_dialog_add_city)
        var btn = dialog.findViewById<TextView>(R.id.open)
        var editText = dialog.findViewById<EditText>(R.id.ip_edit_text)

        btn.setOnClickListener {
           var city = editText.text.toString()
               cities.add(city + " Morocco")
               dialog.dismiss()
               onClick()



        }

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val widthLcl = (displayMetrics.widthPixels * 0.9f).toInt()
        val heightLcl = (displayMetrics.heightPixels * 0.2f).toInt()
        val paramsLcl = dialog.window?.attributes
        paramsLcl?.width = widthLcl
        paramsLcl?.height = heightLcl
        paramsLcl?.gravity = Gravity.CENTER
        dialog.window?.attributes = paramsLcl
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.show()

    }



}