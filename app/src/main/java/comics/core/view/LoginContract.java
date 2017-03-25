package comics.core.view;

/**
 * Created by Renzo D. Santillán Ch. on 25/03/2017.05:59 PM
 * http://rsantillanc.pe.hu/me/
 */
public interface LoginContract {
    interface ViewActions {
        void onGoogleLogin();

        void onFacebookLogin();
    }

    interface LoginView extends Loadable {

    }
}
