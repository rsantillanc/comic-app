package comics.core.model.database.realm;

import comics.core.model.entity.CharacterList;
import comics.core.model.entity.CharacterSummary;
import comics.core.model.entity.Comic;
import comics.core.model.entity.CreatorList;
import comics.core.model.entity.CreatorSummary;
import comics.core.model.entity.Image;
import comics.core.model.entity.Price;
import comics.core.model.entity.SeriesSummary;
import io.realm.annotations.RealmModule;

/**
 * Created by Renzo D. Santillán Ch. on 12/03/2017.08:43 PM
 * http://rsantillanc.pe.hu/me/
 */

@RealmModule(classes = {
        Comic.class,
        Image.class,
        Price.class,
        CharacterList.class,
        CreatorList.class,
        CharacterSummary.class,
        CreatorSummary.class,
        SeriesSummary.class,
})
class ComicModule {}
