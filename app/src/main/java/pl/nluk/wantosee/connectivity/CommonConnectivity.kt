package pl.nluk.wantosee.connectivity

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import pl.nluk.wantosee.connectivity.rest.deserializers.AttractionDeserializer
import pl.nluk.wantosee.connectivity.rest.deserializers.AttractionDetailsDeserializer
import pl.nluk.wantosee.models.Attraction
import pl.nluk.wantosee.models.AttractionDetailsResponse

object CommonConnectivity {
    lateinit var okHttpClient: OkHttpClient
    lateinit var gson : Gson

    fun init(){

        val attractionDeserializer = AttractionDeserializer()
        val attractionDetailsDeserializer = AttractionDetailsDeserializer()

        gson = GsonBuilder()
            .registerTypeAdapter(Attraction::class.java, attractionDeserializer)
            .registerTypeAdapter(AttractionDetailsResponse::class.java, attractionDetailsDeserializer)
            .create()
        okHttpClient = OkHttpClient.Builder().build()
    }
}