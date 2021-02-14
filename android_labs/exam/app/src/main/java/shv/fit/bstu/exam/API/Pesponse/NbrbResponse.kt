package shv.fit.bstu.exam.API.Pesponse

import com.google.gson.annotations.SerializedName

class NbrbResponse {
    @SerializedName("Cur_ID")
    var id: Int? = null
    @SerializedName("Cur_ParentID")
    var parentId: Int? = null
    @SerializedName("Cur_Code")
    var code: String? = null
    @SerializedName("Cur_Abbreviation")
    var abbreviation: String? = null
    @SerializedName("Cur_Name")
    var name: String? = null
    @SerializedName("Cur_Name_Bel")
    var nameBel: String? = null
    @SerializedName("Cur_Name_Eng")
    var nameEng: String? = null
    @SerializedName("Cur_QuotName")
    var quotName: String? = null
    @SerializedName("Cur_QuotName_Bel")
    var quotNameBel: String? = null
    @SerializedName("Cur_QuotName_Eng")
    var quotNameEng: String? = null
    @SerializedName("Cur_NameMulti")
    var nameMulti: String? = null
    @SerializedName("Cur_Name_BelMulti")
    var nameBelMulti: String? = null
    @SerializedName("Cur_Name_EngMulti")
    var nameEngMulti: String? = null
    @SerializedName("Cur_Scale")
    var scale: Int? = null
    @SerializedName("Cur_Periodicity")
    var periodicity: Int? = null
    @SerializedName("Cur_DateStart")
    var dateStart: String? = null
    @SerializedName("Cur_DateEnd")
    var dateEnd: String? = null
}