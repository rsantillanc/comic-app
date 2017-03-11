package comics.core.model.rest;

import comics.core.model.manager.BaseDataManager;
import retrofit2.Response;


/**
 * Created by Renzo D. Santillán Ch. on 11/03/2017.08:57 AM
 * http://rsantillanc.pe.hu/me/
 */

public interface IRest {
    void onRestResponse(Response response, @BaseDataManager.Type int type);

    void onRestFailure(Throwable exception);
}
