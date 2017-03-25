package comics.ui.login;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import butterknife.BindView;
import comics._utility.NetworkUtility;
import comics.core.view.LoginContract;
import comics.ui.BaseActivity;
import pe.nextdots.comics.R;

public class LoginActivity extends BaseActivity implements LoginContract.LoginView, Animator.AnimatorListener {

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


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        playAnimations();
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
}
