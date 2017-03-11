package comics.core.model.manager;

import android.util.Log;

import java.io.IOException;

import comics._utility.C;
import comics.core.model.deserialize.ComicDeserializer;
import comics.core.model.entity.ComicDataWrapper;
import comics.core.model.rest.Connection;
import comics.core.model.rest.RestAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Renzo D. Santillán Ch. on 10/03/2017.01:14 AM
 * http://rsantillanc.pe.hu/me/
 */

public class ComicDataManager extends BaseDataManager {
    private int limit;

    private ComicDataManager() {
        createRestApi();
    }

    public static ComicDataManager newInstance() {
        return new ComicDataManager();
    }


    public void addLimit(int _limit) {
        this.limit = _limit;
    }

    @Override
    void createRestApi() {
        RestAdapter restAdapter = new RestAdapter(Connection.URL_BASE);
        ComicDeserializer deserialize = new ComicDeserializer();
        api = restAdapter.createApiWithCustomConverter(ComicDataWrapper.class, deserialize);
    }

    public void getComicsFromServer() {
        getComics();
    }

    private void getComics() {

        api.getComics(limit, apiKey, timestamp, hash).enqueue(new Callback<ComicDataWrapper>() {
            @Override
            public void onResponse(Call<ComicDataWrapper> call, Response<ComicDataWrapper> response) {
                if (response.isSuccessful()) {
                    Log.d(C.Tag.REST, "onResponse " + response.body().toString());
                    return;
                }

                try {
                    Log.d(C.Tag.REST, "onResponse error " + response.errorBody().string());
                } catch (IOException e) {
                    Log.d(C.Tag.REST, "onResponse error exception " + e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<ComicDataWrapper> call, Throwable t) {
                Log.d(C.Tag.REST, "onFailure " + t.getMessage());
            }
        });

//        api.getComics().enqueue(new Callback<ComicDataWrapper>() {
//            @Override
//            public void onResponse(Call<ComicDataWrapper> call, Response<ComicDataWrapper> response) {
//                Log.d(C.Tag.REST, "onResponse " + response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<ComicDataWrapper> call, Throwable t) {
//                Log.d(C.Tag.REST, "onFailure " + t.getMessage());
//            }
//        });
    }
}
