package pl.nluk.wantosee.connectivity.rest.deserializers


import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import pl.nluk.wantosee.models.AttractionDetailsResponse
import pl.nluk.wantosee.models.Review
import java.lang.reflect.Type


class AttractionDetailsDeserializer : JsonDeserializer<AttractionDetailsResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): AttractionDetailsResponse? {
        json?.let { it.asJsonObject.get("result").asJsonObject }
            ?.run {
                val attractionDetails = AttractionDetailsResponse()
                attractionDetails.name = get("name").asString
                attractionDetails.formattedAddress = get("formatted_address").asString
                attractionDetails.rating = get("rating").asDouble
                val photos = get("photos").asJsonArray
                val reviews = get("reviews").asJsonArray
                attractionDetails.photoReferences.addAll(photos.map { it.asJsonObject.get("photo_reference").asString })
                attractionDetails.reviews.addAll(reviews.map {
                    it.asJsonObject.run {
                        val review = Review()
                        review.authorName = get("author_name").asString
                        review.rating = get("rating").asInt
                        review.reviewText = get("text").asString
                        review
                    }
                })

                return attractionDetails
            }

        return null
    }
}