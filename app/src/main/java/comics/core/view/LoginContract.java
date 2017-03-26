package comics.core.view;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/**
 * Created by Renzo D. Santillán Ch. on 25/03/2017.05:59 PM
 * http://rsantillanc.pe.hu/me/
 */
public interface LoginContract {
    interface ViewActions {
        void onGoogleLogin();

        void onFacebookLogin();

        void onFirebaseAuthWithGoogle(GoogleSignInAccount account);
    }

    interface LoginView extends Loadable {

    }
}
