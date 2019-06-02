package pl.nluk.wantosee.retrofit

import pl.nluk.wantosee.database.CountryDB
import pl.nluk.wantosee.models.Country
import retrofit2.Call
import retrofit2.http.GET
import java.util.concurrent.Callable

interface CountriesInterface {

    @GET("all?filters=name")
    fun countryNames() : Call<List<Country>>
}