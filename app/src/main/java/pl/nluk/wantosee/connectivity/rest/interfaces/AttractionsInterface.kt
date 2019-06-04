package pl.nluk.wantosee.connectivity.rest.interfaces

import pl.nluk.wantosee.BuildConfig
import pl.nluk.wantosee.models.AttractionDetailsResponse
import pl.nluk.wantosee.models.AttractionsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AttractionsInterface {

    companion object Endpoint {
        private const val DETAILS = "details/json"
        private const val TEXT_SEARCH = "textsearch/json"
        private const val KEY = "key=" + BuildConfig.PlacesApiKey
        private const val DETAIL_FIELDS =
            "fields=name,formatted_address,photos/photo_reference,rating,reviews/author_name,reviews/rating,reviews/text"
        private const val INPUT_TYPE = "inputtype=textquery"
        fun placesOfInteres(countryName: String): String {
            return "places of interest $countryName"
        }
    }


    @GET("$TEXT_SEARCH?$KEY&$INPUT_TYPE")
    fun getAttractionsByQuery(@Query(value = "input", encoded = true) query: String): Call<AttractionsResponse>

    @GET("$DETAILS?$KEY&$DETAIL_FIELDS")
    fun getAttractionDetails(@Query(value = "placeid", encoded = true) placeid: String): Call<AttractionDetailsResponse>
}