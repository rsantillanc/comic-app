package comics.core.model.rest;

import comics.core.model.entity.ComicDataWrapper;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Renzo D. Santillán Ch. on 23/02/2017.12:41 AM
 * http://rsantillanc.pe.hu/me/
 */

public interface MarvelApi {

    @GET("comics")
    Call<ComicDataWrapper> getComics(
            @Query("limit") int limit,
            @Query("apikey") String apikey,
            @Query("ts") long timestamp,
            @Query("hash") String hash);

}
