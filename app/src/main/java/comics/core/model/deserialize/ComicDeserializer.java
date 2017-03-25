package comics.core.model.deserialize;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import comics._utility.MapperUtility;
import comics.core.model.entity.Comic;
import comics.core.model.entity.ComicDataWrapper;

/**
 * Created by Renzo D. Santillán Chavez on 10/03/2017.
 */

public class ComicDeserializer implements JsonDeserializer<ComicDataWrapper> {


    @Override
    public ComicDataWrapper deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final String DATA = "data";
        final String RESULTS = "results";


        //Convert to JsonObject
        JsonObject response = json.getAsJsonObject();

        //Make DataWrapper
        ComicDataWrapper wrapper = new ComicDataWrapper();
        wrapper.setCode(response.get("code").getAsInt());
        wrapper.setStatus(response.get("status").getAsString());

        //find comic arrays
        JsonArray comicArrays = response
                .get(DATA).getAsJsonObject() //ComicDataContainer
                .get(RESULTS).getAsJsonArray(); //Comics into array

        //loop comics
        for (JsonElement comicElement : comicArrays) {
            wrapper.addComic(MapperUtility.transformModel((JsonObject) comicElement,Comic.class));
        }

        //Return built data
        return wrapper;
    }
}
