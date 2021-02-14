package shv.fit.bstu.API.mp_lab13

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import shv.fit.bstu.mp_lab13.API.Response.NasaResponse
import shv.fit.bstu.mp_lab13.API.Response.WeatherResponse
import shv.fit.bstu.mp_lab13.API.Response.NumberResponse

interface Service {
    @GET("data/2.5/weather?")
    fun getCurrentWeatherData(@Query("q") City : String, @Query("appid") app_id: String): Call<WeatherResponse>

    @Headers("Content-Type: application/json")
    @GET("random/math")
    fun getNumberInfo(): Call<NumberResponse>


    @Headers("Content-Type: application/json")
    @GET("planetary/apod?")
    fun getApodInfo(@Query("api_key") api_key : String): Call<NasaResponse>
}