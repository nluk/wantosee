package pl.nluk.wantosee.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.fragment_attractions.*
import pl.nluk.wantosee.R
import pl.nluk.wantosee.base.BaseFragment
import pl.nluk.wantosee.database.CountryDB
import pl.nluk.wantosee.fragments.adapters.CountryAdapter
import pl.nluk.wantosee.models.Country
import pl.nluk.wantosee.retrofit.CallbackWrapper
import pl.nluk.wantosee.retrofit.Rest

class AttractionsFragment : BaseFragment() {


    var selectedCountry: Country? = null
    private val countries: MutableList<Country> = ArrayList()
    private lateinit var countriesAdapter: CountryAdapter


    override fun layoutRes() = R.layout.fragment_attractions

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapters()
        setListeners()
        selectCountries()

    }

    private fun setListeners() {
        autocompleteCountryName.setOnItemClickListener { _, _, _, id ->
            selectedCountry = CountryDB.getCountryById(id)
            selectedCountry?.run {
                getPlacesForCountry()
            }
        }
    }

    private fun getPlacesForCountry() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setAdapters() {
        countriesAdapter = CountryAdapter(context!!, R.layout.item_country, countries)
        autocompleteCountryName.setAdapter(countriesAdapter)

    }

    private fun selectCountries() {
        countries.addAll(CountryDB.getCountries())
        if (countries.isEmpty()) downloadCountries()
        else countriesAdapter.notifyDataSetChanged()
    }

    private fun downloadCountries() {
        Rest.getCountriesRest().countryNames().enqueue(CallbackWrapper({ response ->
            response.body()?.let { newCountries ->
                for (country in newCountries) {
                    Log.println(Log.DEBUG, "ATTR", country.name)
                    CountryDB.insertCountry(country)
                }

                countries.addAll(CountryDB.getCountries())
                countriesAdapter.notifyDataSetChanged()
            }

        }, {
            it.printStackTrace()
        }))
    }


}