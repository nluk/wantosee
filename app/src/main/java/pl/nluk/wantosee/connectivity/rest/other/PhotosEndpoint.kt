package pl.nluk.wantosee.connectivity.rest.other

import pl.nluk.wantosee.BuildConfig

object PhotosEndpoint {
    private const val URL = "https://maps.googleapis.com/maps/api/place/photo?"
    private const val MAX_HEIGHT = 720
    private const val MAX_WIDTH = 1270
    private const val SENSOR = "FALSE"
    private const val KEY = BuildConfig.PlacesApiKey

    fun getUrlForReference(reference: String): String {
        return "${URL}key=$KEY&photoreference=$reference&sensor=$SENSOR&maxheight=$MAX_HEIGHT&maxwidth=$MAX_WIDTH"
    }
}