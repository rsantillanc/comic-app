package comics;

import android.app.Application;

import comics.core.model.database.realm.ConfigureSchema;
import io.realm.Realm;

/**
 * Created by Renzo D. Santillán Ch. on 12/03/2017.09:15 PM
 * http://rsantillanc.pe.hu/me/
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        ConfigureSchema.initSchema();
    }
}
