package comics.core.model.manager;

import comics._utility.SecureUtility;
import comics.core.model.rest.Connection;
import comics.core.model.rest.MarvelApi;
import comics.core.model.rest.RestAdapter;
import pe.nextdots.comics.BuildConfig;

/**
 * Created by Renzo D. Santillán Ch. on 10/03/2017.01:15 AM
 * http://rsantillanc.pe.hu/me/
 */

abstract class BaseDataManager {

    MarvelApi api;
    String apiKey = BuildConfig.API_KEY;
    long timestamp = System.currentTimeMillis();
    String hash = SecureUtility.MakeMd5Hash(timestamp);


    void createRestApi() {
        RestAdapter restAdapter = new RestAdapter(Connection.URL_BASE);
        api = restAdapter.createApi();
    }
}
