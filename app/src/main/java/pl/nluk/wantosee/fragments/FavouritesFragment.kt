package pl.nluk.wantosee.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_favourites.*
import pl.nluk.wantosee.R
import pl.nluk.wantosee.base.BaseFragment
import pl.nluk.wantosee.database.AttractionDB
import pl.nluk.wantosee.database.CountryDB
import pl.nluk.wantosee.fragments.adapters.AttractionAdapter
import pl.nluk.wantosee.models.Attraction


class FavouritesFragment : BaseFragment() {


    init {
        TAG = "FAVOURITES_FRAGMENT"
    }


    val countryMap: MutableMap<Long, String> = HashMap()
    val attractions: MutableList<Attraction> = ArrayList()

    lateinit var attractionsAdapter: AttractionAdapter

    override fun layoutRes(): Int = R.layout.fragment_favourites
    override fun useDefaultBackAction(): Boolean = false
    override fun onBackPressed() = navigate(R.id.action_back_to_all_attractions)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setAdapters()
        selectCountryNames()
        selectAttractions()
    }

    private fun selectCountryNames() {
        for (country in CountryDB.getCountries()) countryMap[country.id] = country.name
    }

    private fun selectAttractions() {
        attractions.addAll(AttractionDB.getAttractions())
    }

    private fun setAdapters() {
        attractionsAdapter = AttractionAdapter(attractions, countryMap) {
            val bundle = bundleOf("id" to it)
            navigateWithBundle(R.id.action_to_attraction_details, bundle)
        }

        attractionRecycler.adapter = attractionsAdapter
        attractionRecycler.layoutManager = LinearLayoutManager(context)


    }

    private fun setListeners() {

        toAllAttractions.setOnClickListener {
            navigate(R.id.action_back_to_all_attractions)
        }


    }

}
