package shv.fit.bstu.exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import shv.fit.bstu.exam.API.Pesponse.NbrbResponse
import shv.fit.bstu.exam.API.ServiceDao

class MainActivity : AppCompatActivity() {
    private val NbrbUrl = "https://www.nbrb.by/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        var infoView: TextView = findViewById(R.id.infoView)

        button.setOnClickListener {
            infoView.text = ""

            val retrofit = Retrofit.Builder()
                .baseUrl(NbrbUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(ServiceDao::class.java)
            val call = service.getNbrbData()

            GlobalScope.launch {
                delay(2000)
                call.enqueue(object : Callback<List<NbrbResponse>> {
                    override fun onResponse(call: Call<List<NbrbResponse>>, response: Response<List<NbrbResponse>>) {
                        if (response.code() == 200) {
                            val listNbrbResponse = response.body()!!
                            val nbrbResponse = listNbrbResponse.get(0)

                            val str = "Id: " + nbrbResponse.id +
                                    "\nParent Id: " + nbrbResponse.parentId +
                                    "\nCode: " + nbrbResponse.code +
                                    "\nAbbreviation: " + nbrbResponse.abbreviation +
                                    "\nName: " + nbrbResponse.name +
                                    "\nName Bel: " + nbrbResponse.nameBel +
                                    "\nName Eng: " + nbrbResponse.nameEng +
                                    "\nQuot Name: " + nbrbResponse.quotName +
                                    "\nQuot Name Bel: " + nbrbResponse.quotNameBel +
                                    "\nQuot Name Eng: " + nbrbResponse.quotNameEng
                                    "\nName Multi: " + nbrbResponse.nameMulti +
                                    "\nName Bel Multi: " + nbrbResponse.nameBelMulti +
                                    "\nName Eng Multi: " + nbrbResponse.nameEngMulti +
                                    "\nScale: " + nbrbResponse.scale +
                                    "\nPeriodicity: " + nbrbResponse.periodicity +
                                    "\nDate Start: " + nbrbResponse.dateStart +
                                    "\nDate End: " + nbrbResponse.dateEnd

                            infoView.text = str + "\n" + listNbrbResponse.count()
                        }
                    }

                    override fun onFailure(call: Call<List<NbrbResponse>>, t: Throwable) {
                        infoView!!.text = t.message
                    }
                })
            }
        }
    }
}