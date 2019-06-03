package pl.nluk.wantosee.connectivity.rest.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import pl.nluk.wantosee.models.Attraction
import java.lang.reflect.Type

class AttractionsDeserializer : JsonDeserializer<Attraction> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Attraction {

    }
}