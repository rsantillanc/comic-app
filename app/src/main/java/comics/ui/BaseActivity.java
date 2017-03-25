package comics.ui;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import butterknife.ButterKnife;
import comics._utility.C;
import pe.nextdots.comics.R;

public abstract class BaseActivity extends AppCompatActivity {
    //For animations duration
    protected static final long DEFAULT_DURATION = 2000L;
    protected int heightScreen;
    protected int widthScreen;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setActivityOrientation();
        bindActivity(this);
    }

    protected void setActivityOrientation() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    protected abstract int getLayoutId();

    protected void bindActivity(Activity activity) {
        ButterKnife.bind(activity);
    }

    protected void replaceFragment(Fragment fragment, @IdRes int resIdContainer) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(resIdContainer, fragment)
                .commit();
    }

    /**
     * This method can make a single/custom Toolbar.
     *
     * @param isCustomView if is false then resIdCustomView is null.
     * @param resIdCustomView   {@link View} to inflate a view into ActionBar.
     */
    protected void createToolbar(boolean isCustomView, int resIdCustomView) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (isCustomView) {
            toolbar.setTitle(C.EMPTY);
            ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                    ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.MATCH_PARENT);
            toolbar.addView(getLayoutInflater().inflate(resIdCustomView, null), params);
        }
        setSupportActionBar(toolbar);
    }

    protected void playAnimations() {
        initHeightWidthSScreen();
    }

    protected void showSnack(View v, int resId) {
        Snackbar.make(v, resId, Snackbar.LENGTH_LONG).show();
    }

    protected void showSnack(View v, String message) {
        Snackbar.make(v, (CharSequence) message, Snackbar.LENGTH_SHORT).show();
    }

    protected void showToast(int resId) {
        Toast.makeText(getApplicationContext(), resId, Toast.LENGTH_LONG).show();
    }

    protected void showToast(String msj) {
        Toast.makeText(getApplicationContext(), msj, Toast.LENGTH_SHORT).show();
    }

    protected void keepScreenOn() {
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_IGNORE_CHEEK_PRESSES |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    protected void initHeightWidthSScreen() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        heightScreen = displaymetrics.heightPixels;
        widthScreen = displaymetrics.widthPixels;
    }

    protected void toggleContainer(View v) {

        Rect r = new Rect();
        v.getWindowVisibleDisplayFrame(r);
        int screenHeight = v.getRootView().getHeight();

        // r.bottom is the position above soft keypad or device button.
        // if keypad is shown, the r.bottom is smaller than that before.
        int keypadHeight = screenHeight - r.bottom;

        Log.d("Super", "keypadHeight = " + keypadHeight);
        // 0.15 ratio is perhaps enough to determine keypad height.
        boolean isToHide = keypadHeight > screenHeight * 0.15;
        v.setVisibility(isToHide ? View.GONE : View.VISIBLE);

    }
}
