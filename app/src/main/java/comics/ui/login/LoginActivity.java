package comics.ui.login;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.BindView;
import comics._utility.C;
import comics._utility.NetworkUtility;
import comics.core.presenter.LoginPresenter;
import comics.core.view.LoginContract;
import comics.ui.BaseActivity;
import pe.nextdots.comics.R;

public class LoginActivity extends BaseActivity implements LoginContract.LoginView, Animator.AnimatorListener, GoogleApiClient.OnConnectionFailedListener {

    private static final int RC_SIGN_IN = 100;
    @BindView(R.id.logo_first_text_view)
    AppCompatTextView firstTextV;

    @BindView(R.id.logo_second_text_view)
    AppCompatTextView secondTextV;

    @BindView(R.id.progressBar)
    ProgressBar loginProgressB;

    @BindView(R.id.google_button)
    Button googleButton;

    @BindView(R.id.facebook_button)
    Button facebookButton;

    @BindView(R.id.foot)
    RelativeLayout footRelativeL;

    private LoginPresenter presenter;
    private GoogleApiClient googleClient;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresenter();
        createAuthSession();
        playAnimations();
    }

    private void createAuthSession() {
        presenter.onCreate();

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("456258983960-1h251kqbficr3582344m5jrislhs1lat.apps.googleusercontent.com")
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        googleClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    private void createPresenter() {
        presenter = new LoginPresenter();
        presenter.attachView(this);
        presenter.initialize();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            showLoader(true);
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Log.d(C.Tag.MAIN, "GoogleSignInResult " + result.getStatus());
            if (result.isSuccess())
                // Google Sign In was successful, authenticate with Firebase
                presenter.onFirebaseAuthWithGoogle(result.getSignInAccount());
            else {
                showLoader(false);
                showMessage(getString(R.string.error_session));
            }
        }
    }

    @Override
    public boolean isThereInternet() {
        if (!NetworkUtility.isThereInternetConnection(this)) {
            showNetworkErrorMessage(R.string.error_no_internet);
            return false;
        }
        return true;
    }

    @Override
    public void showNetworkErrorMessage(@StringRes int noInternetResId) {
        Snackbar.make(loginProgressB, noInternetResId, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.offline, view -> {
                })
                .setActionTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .show();
    }

    @Override
    public void showLoader(boolean isLoading) {
        loginProgressB.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void tryAgain() {
        //has doesn't use.
    }

    @Override
    public void showMessage(String message) {
        showSnack(loginProgressB, message);
    }

    @Override
    public Context context() {
        return getApplicationContext();
    }

    @Override
    public Intent intent() {
        return getIntent();
    }

    @Override
    public void setupUiElements() {
        bindActivity(this);
        setupButtons();
    }

    private void setupButtons() {
        googleButton.setOnClickListener(this::googleClicked);
        facebookButton.setOnClickListener(this::facebookClicked);
    }

    private void facebookClicked(View view) {
        presenter.onFacebookLogin();
    }

    private void googleClicked(View view) {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void finishActivity() {
        this.finish();
    }

    @Override
    protected void playAnimations() {
        super.playAnimations();

        //Logo animation
        ObjectAnimator logo1Animator = ObjectAnimator.ofFloat(firstTextV, "translationX", widthScreen / 2, 0);
        ObjectAnimator logo2Animator = ObjectAnimator.ofFloat(secondTextV, "translationX", -widthScreen / 2, 0);

        //Play together
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(DEFAULT_DURATION);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.play(logo1Animator).with(logo2Animator);
        animatorSet.addListener(this);
        animatorSet.start();
    }

    @Override
    public void onAnimationStart(Animator animator) {
        animateLoginButton(DISAPPEAR, true, footRelativeL);
    }

    @Override
    public void onAnimationEnd(Animator animator) {
        animateLoginButton(APPEAR, false, footRelativeL);
    }

    @Override
    public void onAnimationCancel(Animator animator) {

    }

    @Override
    public void onAnimationRepeat(Animator animator) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
