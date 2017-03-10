package comics.core.model.manager;

import comics.core.model.rest.Connection;
import comics.core.model.rest.MarvelApi;
import comics.core.model.rest.RestAdapter;

/**
 * Created by Renzo D. Santillán Ch. on 10/03/2017.01:15 AM
 * http://rsantillanc.pe.hu/me/
 */

public abstract class BaseDataManager {

    protected MarvelApi api;

    void createRestApi(){
        RestAdapter restAdapter = new RestAdapter(Connection.URL_BASE);
        api = restAdapter.createApi();
    }
}
