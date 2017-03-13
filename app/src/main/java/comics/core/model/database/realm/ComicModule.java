package comics.core.model.database.realm;

import comics.core.model.entity.Comic;
import comics.core.model.entity.Image;
import comics.core.model.entity.Price;
import io.realm.annotations.RealmModule;

/**
 * Created by Renzo D. Santillán Ch. on 12/03/2017.08:43 PM
 * http://rsantillanc.pe.hu/me/
 */

@RealmModule(classes = {
        Comic.class,
        Image.class,
        Price.class
})
public class ComicModule {}
