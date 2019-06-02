package pl.nluk.wantosee.models

import com.google.gson.annotations.SerializedName

class Country(id: Long, name: String) {

    var id : Long = 0L

    @SerializedName("name")
    var name : String = ""
}