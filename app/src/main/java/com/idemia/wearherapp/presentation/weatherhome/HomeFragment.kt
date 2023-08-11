package com.idemia.wearherapp.presentation.weatherhome

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.idemia.wearherapp.MainActivity
import com.idemia.wearherapp.R
import com.idemia.wearherapp.data.models.WeatherResponse
import com.idemia.wearherapp.databinding.FragmentHomeBinding
import com.idemia.wearherapp.presentation.weatherhome.adapter.WeatherDataApdpter
import com.idemia.wearherapp.utils.di.interfaces.OnclickItem


class HomeFragment : Fragment() ,OnclickItem , LocationListener{
    lateinit var binding: FragmentHomeBinding
    private  val viewModel: HomeViewModel by activityViewModels()
    private var listDataCities = mutableListOf<WeatherResponse>()
    val REQUEST_LOCATION_PERMISSION = 10000
    val MIN_TIME_BETWEEN_UPDATES: Long = 1000 * 60 * 5
    val MIN_DISTANCE_CHANGE_FOR_UPDATES: Float = 10f



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        var adapter = WeatherDataApdpter(listDataCities,this)
        binding.list.layoutManager = LinearLayoutManager(requireContext())
        binding.list.adapter = adapter
        binding.add.setOnClickListener{
            (requireActivity() as MainActivity).showDialogAddCity {

                getweatherData()
            }
        }
        binding.refresh.setOnRefreshListener {
            binding.refresh.isRefreshing = false
            getweatherData()
        }
        checkPermissions()
        return binding.root
    }


    override fun onResume() {
        super.onResume()
        getweatherData()
        observedata()
    }


    fun setAdapter(){
        var adapter = WeatherDataApdpter(listDataCities.distinct(),this)
        binding.list.adapter = adapter
    }


    // fetching data
    fun getweatherData(){
        binding.content.visibility = View.GONE
        binding.progess.visibility = View.VISIBLE
        listDataCities = mutableListOf<WeatherResponse>()
       MainActivity.cities.forEach{

               viewModel.getWeatherByLocation(it,"1")

        }
    }

    // observing data changes
    fun observedata(){
        viewModel.weather.observe(viewLifecycleOwner){
            listDataCities.add(it)
                binding.content.visibility = View.VISIBLE
                binding.progess.visibility = View.GONE
                setAdapter()


        }
        viewModel.error.observe(viewLifecycleOwner){
            (requireActivity() as MainActivity).showDialog(it.message)

        }
        viewModel.cityLatlong.observe(viewLifecycleOwner){
            it?.let {
                setdataCurrentplace(it)
            }
        }

    }



    // setting data to items of current position

    fun setdataCurrentplace(response : WeatherResponse){
        binding.currentposition.content.visibility = View.VISIBLE
        binding.currentposition.loading.visibility = View.GONE

        binding.currentposition.position.text = response.data.request[0].query
        binding.currentposition.degree.text = response.data.currentCondition[0].temp_C
        Glide.with(requireContext()).load(response.data.currentCondition[0].weatherIconUrl[0].value).placeholder(R.drawable.ic_baseline_data_holding_24).dontAnimate().into(binding.currentposition.cloud);
       binding.currentposition.cloudtext.text = response.data.currentCondition[0].weatherDesc[0].value
    }


    override fun onclickItem(position: Int) {

        listDataCities[position].let {

            var data = it
            var bundle = Bundle()
            bundle.putSerializable("weatherdetail",data.data)

            findNavController().navigate(R.id.detail,bundle)

        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, start location updates

                getLocation()
            } else {
                // Permission denied, handle accordingly
            Toast.makeText(requireContext(),"acces to location denied",Toast.LENGTH_LONG).show()
             }
        }
    }

    // Check for permissions and request if needed
    fun checkPermissions(){
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED) {
            getLocation()
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }


    // getting location of the current user
    fun getLocation(){
        // getting  lat and long updates when the device's location changes


        val locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            MIN_TIME_BETWEEN_UPDATES,
            MIN_DISTANCE_CHANGE_FOR_UPDATES,
            this
        )
    }

    override fun onLocationChanged(location: Location) {
                val latitude = location.latitude
                val longitude = location.longitude
        viewModel.getLocationLatLong(latitude,longitude,"A")
        binding.currentposition.content.visibility = View.INVISIBLE
        binding.currentposition.loading.visibility = View.VISIBLE
    }




}