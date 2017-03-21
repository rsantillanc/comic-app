package comics.core.model.manager;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import comics._utility.SecureUtility;
import comics.core.model.rest.Connection;
import comics.core.model.rest.IRest;
import comics.core.model.rest.MarvelApi;
import comics.core.model.rest.RestAdapter;
import pe.nextdots.comics.BuildConfig;

/**
 * Created by Renzo D. Santillán Ch. on 10/03/2017.01:15 AM
 * http://rsantillanc.pe.hu/me/
 */

public abstract class BaseDataManager implements IRest {

    protected static final int COMIC = 100;

    @IntDef({COMIC})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    MarvelApi api;
    String apiKey = BuildConfig.API_KEY;
    long timestamp = System.currentTimeMillis();
    String hash = SecureUtility.MakeMd5Hash(timestamp);


    void createRestApi() {
        RestAdapter restAdapter = new RestAdapter(Connection.URL_BASE);
        api = restAdapter.createApi();
    }
}
