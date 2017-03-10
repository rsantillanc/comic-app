package comics.core.model.manager;

import comics.core.model.deserialize.ComicDeserializer;
import comics.core.model.entity.ComicDataWrapper;
import comics.core.model.rest.Connection;
import comics.core.model.rest.RestAdapter;

/**
 * Created by Renzo D. Santillán Ch. on 10/03/2017.01:14 AM
 * http://rsantillanc.pe.hu/me/
 */

public class ComicDataManager extends BaseDataManager {

    public ComicDataManager() {
        createRestApi();
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

    }
}
