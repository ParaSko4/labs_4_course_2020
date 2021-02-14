package shv.fit.bstu.mp_lab13

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import shv.fit.bstu.API.mp_lab13.Service
import shv.fit.bstu.mp_lab13.API.Response.NasaResponse
import shv.fit.bstu.mp_lab13.API.Response.NumberResponse
import shv.fit.bstu.mp_lab13.API.Response.WeatherResponse
import java.io.InputStream
import java.net.URL


class MainActivity : AppCompatActivity() {
    private val WeatherUrl = "http://api.openweathermap.org/"
    private val WeatherKey = "38ca0b3f4d7bdb51c3b1d2c2c6fefd35"

    private val NumberUrl = "http://numbersapi.com/"
    private val NasaUrl = "https://api.nasa.gov/"
    private val NasaKey = "hATdbVDR5elbqymoWQjnUm5oJWhJ0NgbYjB5qaJV"

    private var weatherText: TextView? = null
    private var inputCity: EditText? = null

    private var numberText: TextView? = null

    private var nasaText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherText = findViewById(R.id.weatherText)
        inputCity = findViewById(R.id.inputCity)
        numberText = findViewById(R.id.numberText)
        nasaText = findViewById(R.id.nasaText)

        val weatherButton: Button = findViewById(R.id.button)
        val numberButton: Button = findViewById(R.id.numberButton)
        val nasaButton: Button = findViewById(R.id.nasaButton)
        val imageView: ImageView = findViewById(R.id.imageView)

        weatherButton.setOnClickListener {
            weatherText!!.text = ""

            val retrofit = Retrofit.Builder()
                .baseUrl(WeatherUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            GlobalScope.launch {
                val service = retrofit.create(Service::class.java)
                val call = service.getCurrentWeatherData(inputCity!!.text.toString(), WeatherKey)

                call.enqueue(object : Callback<WeatherResponse> {
                    override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                        if (response.code() == 200) {
                            val weatherResponse = response.body()!!

                            val str = "Country: " +
                                    weatherResponse.sys!!.country +
                                    "\n" +
                                    "Temperature: " +
                                    (weatherResponse.main!!.temp - 273) +
                                    "\n" +
                                    "Temperature(Min): " +
                                    (weatherResponse.main!!.temp_min - 273) +
                                    "\n" +
                                    "Temperature(Max): " +
                                    (weatherResponse.main!!.temp_max - 273) +
                                    "\n" +
                                    "Humidity: " +
                                    weatherResponse.main!!.humidity +
                                    "\n" +
                                    "Pressure: " +
                                    weatherResponse.main!!.pressure +
                                    "\n" +
                                    "Sunrise: " +
                                    weatherResponse.sys!!.sunrise +
                                    "\n" +
                                    "Sunset: " +
                                    weatherResponse.sys!!.sunset

                            weatherText!!.text = str
                        }
                    }

                    override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                        weatherText!!.text = t.message
                    }
                })
            }
        }

        numberButton.setOnClickListener {
            numberText!!.text = ""

            val retrofit = Retrofit.Builder()
                .baseUrl(NumberUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            GlobalScope.launch {
                val service = retrofit.create(Service::class.java)
                val call = service.getNumberInfo()

                call.enqueue(object : Callback<NumberResponse> {
                    override fun onResponse(call: Call<NumberResponse>, response: Response<NumberResponse>) {
                        if (response.code() == 200) {
                            val numberResponse = response.body()!!

                            val str = "Text: " +
                                    numberResponse.text +
                                    "\n" +
                                    "Number: " +
                                    numberResponse.number +
                                    "\n" +
                                    "Found: " +
                                    numberResponse.found +
                                    "\n" +
                                    "Type: " +
                                    numberResponse.type

                            numberText!!.text = str
                        }
                    }

                    override fun onFailure(call: Call<NumberResponse>, t: Throwable) {
                        numberText!!.text = t.message
                    }
                })
            }
        }

        nasaButton.setOnClickListener {
            nasaText!!.text = ""

            val retrofit = Retrofit.Builder()
                .baseUrl(NasaUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            GlobalScope.launch {
                val service = retrofit.create(Service::class.java)
                val call = service.getApodInfo(NasaKey)

                call.enqueue(object : Callback<NasaResponse> {
                    override fun onResponse(call: Call<NasaResponse>, response: Response<NasaResponse>) {
                        if (response.code() == 200) {
                            val nasaResponse = response.body()!!

                            val str = "Title: " +
                                    nasaResponse.title +
                                    "\n" +
                                    "Explanation: " +
                                    nasaResponse.explanation

                            val di: DownloadImageTask = DownloadImageTask(imageView)
                            di.execute(nasaResponse.url);

                            nasaText!!.text = str
                        }
                    }

                    override fun onFailure(call: Call<NasaResponse>, t: Throwable) {
                        nasaText!!.text = t.message
                    }
                })
            }
        }
    }

    private class DownloadImageTask(var bmImage: ImageView) :
        AsyncTask<String?, Void?, Bitmap?>() {
        protected override fun doInBackground(vararg p0: String?): Bitmap? {
            val urldisplay = p0[0]
            var mIcon11: Bitmap? = null
            try {
                val `in`: InputStream = URL(urldisplay).openStream()
                mIcon11 = BitmapFactory.decodeStream(`in`)
            } catch (e: Exception) {
                Log.e("Error IMAGE: ", e.message!!)
                e.printStackTrace()
            }
            return mIcon11
        }

        override fun onPostExecute(result: Bitmap?) {
            bmImage.setImageBitmap(result)
        }
    }
}