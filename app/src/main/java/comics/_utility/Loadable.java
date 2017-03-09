package comics._utility;

import android.support.annotation.StringRes;

import comics.core.view.View;

/**
 * Created by Renzo D. Santillán Chavez on 09/03/2017.
 */

public interface Loadable extends View{
    boolean isThereInternet();

    void showInternetErrorMessage(@StringRes int noInternetResId);

    void showLoader(boolean isLoading);

    void tryAgain();

    void showMessage(String message);
}
