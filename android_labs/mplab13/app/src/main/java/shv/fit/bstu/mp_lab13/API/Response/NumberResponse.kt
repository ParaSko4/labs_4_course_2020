package shv.fit.bstu.mp_lab13.API.Response

import com.google.gson.annotations.SerializedName

class NumberResponse {
    @SerializedName("text")
    var text: String? = null
    @SerializedName("number")
    var number: Int = 0
    @SerializedName("found")
    var found: Boolean? = null
    @SerializedName("type")
    var type: String? = null
}