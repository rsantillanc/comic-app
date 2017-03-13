package comics.core.model.database.realm;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Renzo D. Santillán Ch. on 12/03/2017.09:38 PM
 * http://rsantillanc.pe.hu/me/
 */

public class ConfigureSchema {
    public static void initSchema() {
        // Set the module in the RealmConfiguration to allow only classes defined by the module.
        RealmConfiguration config = new RealmConfiguration.Builder()
                .modules(new ComicModule())
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
