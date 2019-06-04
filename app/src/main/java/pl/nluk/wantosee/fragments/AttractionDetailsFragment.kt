package pl.nluk.wantosee.fragments

import android.os.Bundle
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_attraction_details.*
import pl.nluk.wantosee.R
import pl.nluk.wantosee.base.BaseFragment
import pl.nluk.wantosee.connectivity.rest.CallbackWrapper
import pl.nluk.wantosee.connectivity.rest.Rest
import pl.nluk.wantosee.connectivity.rest.other.PhotosEndpoint
import pl.nluk.wantosee.database.AttractionDB
import pl.nluk.wantosee.database.CountryDB
import pl.nluk.wantosee.models.Attraction
import pl.nluk.wantosee.models.AttractionDetailsResponse


class AttractionDetailsFragment : BaseFragment() {


    init {
        TAG = "ATTRACTION_DETAILS_FRAGMENT"
    }


    var attraction: Attraction? = null
    var countryName: String = ""
    lateinit var attractionDetails: AttractionDetailsResponse

    override fun layoutRes(): Int = R.layout.fragment_attraction_details
    override fun useDefaultBackAction(): Boolean = false
    override fun onBackPressed() = navigate(R.id.action_details_back_to_favourites)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectAttraction()
        selectCountryName()
        getAttractionDetails()
    }

    private fun setViewsData() {
        attractionAddress.text = attractionDetails.formattedAddress
        attractionName.text = attractionDetails.name
        attractionRating.rating = attractionDetails.rating.toFloat()
        attractionCountry.text = countryName
        Picasso.get()
            .load(PhotosEndpoint.getUrlForReference(attraction!!.imageReference))
            .into(attractionImage)

    }

    private fun getAttractionDetails() {
        Rest.getAttractionsRest().getAttractionDetails(attraction!!.googleId).enqueue(
            CallbackWrapper(
                { response ->
                    if (response.isSuccessful) {
                        response.body()?.let {
                            attractionDetails = it
                        }
                        setViewsData()
                    }
                },
                { error ->
                    error.printStackTrace()
                })
        )
    }

    private fun selectCountryName() {
        countryName = CountryDB.getCountryNameById(attraction!!.countryId) ?: ""
    }

    private fun selectAttraction() {
        arguments?.get("id")?.let {
            attraction = AttractionDB.getAttractionById(it as Long)
        }

        if (attraction == null) throw IllegalStateException("Details called for nonexistent attraction")
    }


}
