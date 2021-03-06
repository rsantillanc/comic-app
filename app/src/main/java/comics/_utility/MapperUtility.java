package comics._utility;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * Created by Renzo D. Santillán Ch. on 23/02/2017.12:45 AM
 * http://rsantillanc.pe.hu/me/
 */
public class MapperUtility {

    public static <T> T transformModel(@NonNull final JsonObject body, @NonNull final Class<T> classModel) {
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat(C.DEFAULT_DATE);
        return builder.create().fromJson(body, classModel);
    }

    public static String transformModelToJson(Object model) {
        return new Gson().toJson(model);
    }
}
