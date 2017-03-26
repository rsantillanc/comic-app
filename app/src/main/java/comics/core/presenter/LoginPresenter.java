package comics.core.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import comics._utility.C;
import comics._utility.Navigator;
import comics.core.view.LoginContract;

import static android.content.ContentValues.TAG;


/**
 * Created by Renzo D. Santillán Ch. on 25/03/2017.06:01 PM
 * http://rsantillanc.pe.hu/me/
 */

public class LoginPresenter extends BasePresenter<LoginContract.LoginView> implements LoginContract.ViewActions {


    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    public void initialize() {
        mvpView.setupUiElements();
        firebaseAuth = FirebaseAuth.getInstance();
        if (authStateListener == null)
            authStateListener = firebaseAuth1 -> {
                FirebaseUser user = firebaseAuth1.getCurrentUser();
                if (user != null) {
                    saveUser(user);
                    Navigator.goToMainActivity(mvpView.context());
                    mvpView.finishActivity();
                } else
                    mvpView.showMessage("User is signed out");
            };
    }

    private void saveUser(FirebaseUser user) {
        SharedPreferences pref = mvpView.context().getSharedPreferences(C.DEFAULT_DATE, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(C.Key.USER_NAME, user.getDisplayName()).apply();
        edit.putString(C.Key.USER_EMAIL, user.getEmail()).apply();
        edit.putString(C.Key.USER_PHOTO, user.getPhotoUrl().toString()).apply();
        Log.d(C.Tag.MAIN, "FirebaseUser getDisplayName-> " + user.getDisplayName());
        Log.d(C.Tag.MAIN, "FirebaseUser getEmail-> " + user.getEmail());
        Log.d(C.Tag.MAIN, "FirebaseUser getProviderId-> " + user.getProviderId());
        Log.d(C.Tag.MAIN, "FirebaseUser getPhotoUrl-> " + user.getPhotoUrl());
        Log.d(C.Tag.MAIN, "FirebaseUser getUid-> " + user.getUid());
    }

    @Override
    public void onCreate() {
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onDestroy() {
        if (authStateListener != null)
            firebaseAuth.removeAuthStateListener(authStateListener);
//        FirebaseAuth.getInstance().signOut();
    }

    @Override
    public void onDone(Object object) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onTimeout() {

    }

    @Override
    public void onConnectError() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onGoogleLogin(GoogleSignInAccount account) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    Log.d(C.Tag.MAIN, "signInWithCredential:onComplete:" + task.isSuccessful());
                    mvpView.showLoader(false);
                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
                    if (!task.isSuccessful())
                        mvpView.showMessage("Authentication failed.");
                });
    }

    @Override
    public void onFacebookLogin() {

    }

}
