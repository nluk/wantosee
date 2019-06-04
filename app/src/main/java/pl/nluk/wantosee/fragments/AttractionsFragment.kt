package pl.nluk.wantosee.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_attractions.*
import pl.nluk.wantosee.R
import pl.nluk.wantosee.base.BaseFragment
import pl.nluk.wantosee.connectivity.rest.CallbackWrapper
import pl.nluk.wantosee.connectivity.rest.Rest
import pl.nluk.wantosee.connectivity.rest.interfaces.AttractionsInterface
import pl.nluk.wantosee.database.AttractionDB
import pl.nluk.wantosee.database.CountryDB
import pl.nluk.wantosee.fragments.adapters.CountryAdapter
import pl.nluk.wantosee.fragments.adapters.MysteryAttractionAdapter
import pl.nluk.wantosee.models.Attraction
import pl.nluk.wantosee.models.Country
import kotlin.random.Random


class AttractionsFragment : BaseFragment() {


    init {
        TAG = "ATTRACTIONS_FRAGMENT"
    }


    var selectedCountry: Country? = null
    private val countries: MutableList<Country> = ArrayList()
    private lateinit var countriesAdapter: CountryAdapter


    private val attractions: MutableList<Attraction> = ArrayList()
    private lateinit var attractionsAdapter: MysteryAttractionAdapter


    override fun layoutRes() = R.layout.fragment_attractions
    override fun useDefaultBackAction(): Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapters()
        setListeners()
        selectCountries()
        selectRandomCountry()
    }

    private fun selectRandomCountry() {
        selectedCountry = countries[Random.nextInt(countries.size)]
        autocompleteCountryName.setText(selectedCountry!!.name)
        downloadAttractions(selectedCountry!!)
    }

    private fun downloadAttractions(country: Country) {
        loaderAnim.smoothToShow()
        attractions.clear()
        Rest.getAttractionsRest()
            .getAttractionsByQuery(AttractionsInterface.placesOfInteres(country.name))
            .enqueue(
                CallbackWrapper({ response ->
                    val downloadedAttractions: MutableList<Attraction> = ArrayList()
                    if (response.isSuccessful) {
                        response.body()?.run {
                            for (attraction in attractions) {
                                attraction.countryId = selectedCountry!!.id
                                downloadedAttractions.add(attraction)
                            }
                        }
                    }
                    attractions.addAll(downloadedAttractions)
                    attractionsAdapter.dataChanged()
                    loaderAnim.smoothToHide()
                },

                    { error ->
                        error.printStackTrace()
                    })
            )


    }

    private fun setListeners() {
        autocompleteCountryName.setOnItemClickListener { _, _, _, id ->
            selectedCountry = CountryDB.getCountryById(id)
            selectedCountry?.let {
                downloadAttractions(it)
            }
        }

        toFavourites.setOnClickListener {
            navigate(R.id.action_to_favourites)
        }

        attractionRecycler.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0 && toFavourites.visibility == View.VISIBLE) {
                        toFavourites.hide()
                    } else if (dy < 0 && toFavourites.visibility != View.VISIBLE) {
                        toFavourites.show()
                    }
                }
            }
        )

    }


    private fun setAdapters() {
        countriesAdapter = CountryAdapter(context!!, R.layout.item_country, countries)
        autocompleteCountryName.setAdapter(countriesAdapter)
        attractionsAdapter =
            MysteryAttractionAdapter(attractions) { attraction -> AttractionDB.insertAttraction(attraction) }
        attractionRecycler.adapter = attractionsAdapter
        attractionRecycler.layoutManager = LinearLayoutManager(context)

    }

    private fun selectCountries() {
        countries.addAll(CountryDB.getCountries())
        if (countries.isEmpty()) {
            downloadCountries()
            loaderAnim.smoothToShow()
        } else {
            countriesAdapter.notifyDataSetChanged()
            loaderAnim.smoothToHide()
        }

    }

    private fun downloadCountries() {
        Rest.getCountriesRest().countryNames().enqueue(
            CallbackWrapper(
                { response ->
                    response.body()?.let { newCountries ->
                        for (country in newCountries) {
                            Log.println(Log.DEBUG, "ATTR", country.name)
                            CountryDB.insertCountry(country)
                        }

                        countries.addAll(CountryDB.getCountries())
                        countriesAdapter.notifyDataSetChanged()

                    }

                },
                {
                    it.printStackTrace()
                })
        )
    }


}