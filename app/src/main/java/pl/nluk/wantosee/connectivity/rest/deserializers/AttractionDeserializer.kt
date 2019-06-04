package pl.nluk.wantosee.connectivity.rest.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import pl.nluk.wantosee.models.Attraction
import java.lang.reflect.Type

class AttractionDeserializer : JsonDeserializer<Attraction> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Attraction? {
        json?.asJsonObject?.run {
            val attraction = Attraction()
            attraction.googleId = get("place_id").asString
            try {
                attraction.imageReference = get("photos").asJsonArray[0].asJsonObject.get("photo_reference").asString
            } catch (exception: IllegalStateException) {

            }
            attraction.name = get("name").asString
            return attraction
        }

        return null
    }
}