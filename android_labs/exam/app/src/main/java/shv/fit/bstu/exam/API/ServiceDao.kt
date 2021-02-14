package shv.fit.bstu.exam.API

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import shv.fit.bstu.exam.API.Pesponse.NbrbResponse

interface ServiceDao {
    @Headers("Content-Type: application/json")
    @GET("api/exrates/currencies")
    fun getNbrbData(): Call<List<NbrbResponse>>
}