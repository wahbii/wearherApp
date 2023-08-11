package  com.idemia.wearherapp.domain.repository
import com.idemia.wearherapp.data.models.WeatherResponse
import com.idemia.wearherapp.data.source.network.response.Resource


interface WeatherRepository {
    suspend fun getWeatherByCity(location : String,days :String) :Resource<WeatherResponse>
    suspend fun getWeatherByLatLong(location : String,days :String) :Resource<WeatherResponse>
}