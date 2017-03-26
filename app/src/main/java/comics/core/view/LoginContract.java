package comics.core.view;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/**
 * Created by Renzo D. Santillán Ch. on 25/03/2017.05:59 PM
 * http://rsantillanc.pe.hu/me/
 */
public interface LoginContract {
    interface ViewActions {

        void onGoogleLogin(GoogleSignInAccount account);

        void onFacebookLogin();

    }

    interface LoginView extends Loadable {

    }
}
