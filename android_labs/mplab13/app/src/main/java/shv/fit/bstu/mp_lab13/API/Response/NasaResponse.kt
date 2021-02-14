package shv.fit.bstu.mp_lab13.API.Response

import com.google.gson.annotations.SerializedName

class NasaResponse {
    @SerializedName("copyright")
    var copyright: String? = null
    @SerializedName("date")
    var date: String? = null
    @SerializedName("explanation")
    var explanation: String? = null
    @SerializedName("hdurl")
    var hdurl: String? = null
    @SerializedName("media_type")
    var media_type: String? = null
    @SerializedName("service_version")
    var service_version: String? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("url")
    var url: String? = null
}