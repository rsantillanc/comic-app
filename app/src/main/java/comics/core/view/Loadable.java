package comics.core.view;

import android.support.annotation.StringRes;

/**
 * Created by Renzo D. Santillán Chavez on 09/03/2017.
 */

public interface Loadable extends View{
    boolean isThereInternet();

    void showNetworkErrorMessage(@StringRes int noInternetResId);

    void showLoader(boolean isLoading);

    void tryAgain();

    void showMessage(String message);
}
