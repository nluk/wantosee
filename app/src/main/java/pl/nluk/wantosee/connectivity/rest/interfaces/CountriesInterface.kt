package pl.nluk.wantosee.connectivity.rest.interfaces

import pl.nluk.wantosee.models.Country
import retrofit2.Call
import retrofit2.http.GET

interface CountriesInterface {

    @GET("all?filters=name")
    fun countryNames() : Call<List<Country>>
}