package pl.nluk.wantosee.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import pl.nluk.wantosee.R
import pl.nluk.wantosee.base.BaseFragment
import pl.nluk.wantosee.database.AppDatabase
import pl.nluk.wantosee.database.CountryDB
import pl.nluk.wantosee.models.Country
import pl.nluk.wantosee.retrofit.CallbackWrapper
import pl.nluk.wantosee.retrofit.Rest
import retrofit2.Response
import retrofit2.Retrofit

class AttractionsFragment : BaseFragment() {

    val countries : MutableList<Country> = ArrayList()


    override fun layoutRes() = R.layout.fragment_attractions

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectCountries()
    }

    private fun selectCountries() {
        countries.addAll(CountryDB.getCountries())
        if(countries.isEmpty()) downloadCountries()
    }

    private fun downloadCountries() {
        Rest.getCountriesRest().countryNames().enqueue(CallbackWrapper({response ->
            response.body()?.let { newCountries ->
                for(country in newCountries){
                    Log.println(Log.DEBUG,"ATTR",country.name)
                    CountryDB.insertCountry(country)
                }

                countries.addAll(CountryDB.getCountries())
            }

        },{
                it.printStackTrace()
        }))
    }


}