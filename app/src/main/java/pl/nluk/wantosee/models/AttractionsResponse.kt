package pl.nluk.wantosee.models

import com.google.gson.annotations.SerializedName

class AttractionsResponse {
    @SerializedName("next_page_token")
    var nextPageToken: String = ""
    @SerializedName("results")
    var attractions: MutableList<Attraction> = ArrayList()
}